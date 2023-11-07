package ten.give.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ten.give.common.utils.JwtUtils;
import ten.give.web.service.LoginService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
// OncePerRequestFilter : Request 에 대해 단 한번만 수행하는 filter
public class JwtFilter extends OncePerRequestFilter {

    private final LoginService loginService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request Header 에서 AUTHORIZATION 꺼내기

            final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("authorization : {}" , authorization);

            // authorization 이 없거나 토큰 앞단에 특정 단어가 없다면 통과 X
            if (authorization == null || !authorization.startsWith("Bearer ")){
                log.error("authorization 을 잘못 보냈습니다.");
                filterChain.doFilter(request,response);
                return;
            }

            //Token 꺼내기
            String token = authorization.split( " ")[1];

            //Token Expired [만료] 되었는지 여부
            if (JwtUtils.isExpired(token,secretKey)){
                log.error("Token이 만료 되었습니다.");
                filterChain.doFilter(request,response);
                return;
            }

        /*// Username Token 에서 꺼내기
        String userName = JwtUtils.getUsername(token,secetKey);
        log.info("userName : {}", userName);*/


            // userId Token 에서 꺼내기
            Long userId = JwtUtils.getUserId(token,secretKey);
            log.info("filter userId : {} ", userId);


            //권한 부여
            //List.of(new SimpleGrantedAuthority("USER")) : DB 에서 지정한 Role 을 넣어줌
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId,null, List.of(new SimpleGrantedAuthority("USER")));

            // Detail 을 넣어준다.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }


    }

