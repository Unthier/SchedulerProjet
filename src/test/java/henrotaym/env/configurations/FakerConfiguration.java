package henrotaym.env.configurations;

import henrotaym.env.enums.ProfileName;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ProfileName.TEST, ProfileName.QUEUE})
public class FakerConfiguration {
  // todo mettre cette classe
  @Bean
  Faker faker() {
    return new Faker();
  }
}
