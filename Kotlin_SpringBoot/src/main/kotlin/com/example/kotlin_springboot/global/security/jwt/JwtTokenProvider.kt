import com.example.kotlin_springboot.domain.user.domain.User
import com.example.kotlin_springboot.global.security.jwt.dto.TokenResponse
import com.example.kotlin_springboot.global.security.jwt.exception.ExpiredTokenException
import com.example.kotlin_springboot.global.security.principle.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date
import javax.servlet.http.HttpServletRequest


@RequiredArgsConstructor
@Component
class JwtTokenProvider (
    private val authDetailsService: AuthDetailsService,
    @Value("\${spring.jwt.key}")
    private var key: String? = null,

    @Value("\${spring.jwt.live.accessToken}")
    private val accessTokenTime: Long? = null

) {
    companion object {
        private const val HEADER = "Authorization"
        private const val PREFIX = "Bearer"
    }

    protected fun init() {
        key = Base64.getEncoder().encodeToString(key!!.toByteArray())
    }

    // 주어진 사용자에 대한 토큰을 생성하여 TokenResponse 객체로 반환
    fun getToken(user: User): TokenResponse {
        val accessToken = generateAccessToken(user.email)
        return TokenResponse(accessToken, accessTokenTime!!)
    }

    // 주어진 사용자 ID로 액세스 토큰을 생성
    fun generateAccessToken(email: String): String {
        return generateToken(email, "access", accessTokenTime)
    }

    // 주어진 사용자 ID, 타입, 만료 시간으로 토큰을 생성
    private fun generateToken(accountId: String, type: String, exp: Long?): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, key!!.toByteArray())
            .setSubject(accountId)
            .claim("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp!! * 1000))
            .compact()
    }

    // 요청의 헤더에서 토큰을 추출
    fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader(HEADER)
        return parseToken(bearer)
    }

    // 토큰을 사용하여 Authentication 객체를 생성
    fun authentication(token: String?): Authentication {
        val userDetails = authDetailsService!!.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // "Bearer" 접두사를 제거하여 토큰을 파싱.
    fun parseToken(bearerToken: String?): String? {
        return if (bearerToken != null && bearerToken.startsWith(PREFIX)) bearerToken.replace(PREFIX, "") else null
    }

    // 키를 사용하여 토큰 본문을 추출
    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(key!!.toByteArray())
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            throw ExpiredTokenException
        }
    }

    // 토큰 본문에서 주제(사용자 ID)를 가져옴
    private fun getTokenSubject(token: String?): String {
        return getTokenBody(token).subject
    }
}
