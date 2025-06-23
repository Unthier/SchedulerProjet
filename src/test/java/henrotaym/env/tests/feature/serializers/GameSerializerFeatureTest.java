package henrotaym.env.tests.feature.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import henrotaym.env.ApplicationTest;
import henrotaym.env.database.factories.GameFactory;
import henrotaym.env.entities.Game;
import henrotaym.env.mappers.GameMapper;
import henrotaym.env.serializers.GameSerializer;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GameSerializerFeatureTest extends ApplicationTest {
  @Autowired GameFactory gameFactory;
  @Autowired GameSerializer gameSerializer;
  @Autowired GameMapper gameMapper;

  @Test
  public void it_serializes_a_game() throws JsonProcessingException {
    Game game = this.gameFactory.create();
    Set<String> include = Set.of("studio");
    String test = this.gameSerializer.serialize(game, include);
    System.out.println();
  }
}
