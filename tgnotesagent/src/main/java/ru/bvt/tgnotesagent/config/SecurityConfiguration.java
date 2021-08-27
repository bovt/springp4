package ru.bvt.tgnotesagent.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bvt.tgnotesagent.security.AuthorDetailsServices;

import javax.sql.DataSource;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthorDetailsServices authorDetailsServices;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // По умолчанию SecurityContext хранится в сессии
                // Это необходимо, чтобы он нигде не хранился
                // и данные приходили каждый раз с запросом
 //               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/login").not().fullyAuthenticated()

                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/api/author","/aindex-cb").hasRole("ADMIN")
                .antMatchers("/api/category","/cindex-cb").hasRole("USER")
                .antMatchers("/api/book","/bindex-cb").hasRole("USER")

                //Доступ разрешен всем пользователей
                .antMatchers("/api/note","/index","/nindex-cb", "/login").permitAll()

                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()

                .and()

                //Настройка для входа в систему
                .formLogin()
//                .loginPage("/login")

                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/index")
                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/login")
                ;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder()
    {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(authorDetailsServices);
    }
    /*    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select name, password, 'true' from author " +
                                "where name=?")
                .authoritiesByUsernameQuery(
                        "select name, 'ROLE_USER' from author " +
                                "where name=?");
    }
*/
}