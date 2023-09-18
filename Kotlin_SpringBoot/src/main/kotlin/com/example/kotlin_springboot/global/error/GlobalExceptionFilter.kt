import com.example.kotlin_springboot.global.error.ErrorResponse
import com.example.kotlin_springboot.global.error.exception.BusinessException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BusinessException) {
            val errorCode = e.errorCode
            val errorResponse = ErrorResponse(errorCode.status, errorCode.message, LocalDateTime.now())
            writeErrorResponse(response, errorCode.status, errorResponse)
        } catch (e: Exception) {
            val errorResponse = ErrorResponse(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Internal Server Error",
                LocalDateTime.now())
            writeErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorResponse)
        }
    }

    @Throws(IOException::class)
    private fun writeErrorResponse(response: HttpServletResponse, status:Int, errorResponse:
    ErrorResponse
    ) {
        response.status = status
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        objectMapper.writeValue(response.writer,errorResponse)
    }
}
