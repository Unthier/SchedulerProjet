package henrotaym.env.configurations;

import henrotaym.env.enums.ProfileName;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ProfileName.QUEUE, ProfileName.SCHEDULER})
public class FakerConfiguration {
  @Bean
  Faker faker() {
    return new Faker();
  }
}
