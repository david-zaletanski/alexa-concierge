package net.dzale.alexa.concierge.config;

import net.dzale.alexa.concierge.service.AlexaConciergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Contains security related configurations and reusable beans.
 *
 * @author dzale
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final int PASSWORD_ENCODER_STRENGTH = 10;

    @Value( "${alexa-concierge.security.enabled:false}" )
    private boolean enabled;

    @Autowired
    AlexaConciergeService alexaConciergeService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        // Disable Cross Site Request Forgery (CSRF) - Usually disabled if API only.
        http.csrf().and().cors().disable();

        // No Security
        // TODO Implement basic authentication for admin endpoints
        http.authorizeRequests().anyRequest().permitAll();
    }

}
