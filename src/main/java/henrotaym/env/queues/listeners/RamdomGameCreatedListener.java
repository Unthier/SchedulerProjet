package henrotaym.env.queues.listeners;

import henrotaym.env.annotations.KafkaRetryableListener;
import henrotaym.env.entities.Game;
import henrotaym.env.enums.ProfileName;
import henrotaym.env.factories.CoverFactory;
import henrotaym.env.factories.GameFactory;
import henrotaym.env.factories.StudioFactory;
import henrotaym.env.factories.TagFactory;
import henrotaym.env.queues.events.RamdomGameCreatedEvent;
import henrotaym.env.repositories.GameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
@Profile(ProfileName.QUEUE)
public class RamdomGameCreatedListener implements Listener<RamdomGameCreatedEvent> {
  private StudioFactory studioFactory;
  private CoverFactory coverFactory;
  private TagFactory tagFactory;
  private GameRepository gRepository;

  @Override
  @KafkaRetryableListener(RamdomGameCreatedEvent.EVENT_NAME)
  public void listen(RamdomGameCreatedEvent event) {
    Faker faker = new Faker();

    this.studioFactory.create();
    GameFactory gFactory =
        new GameFactory(
            faker, this.gRepository, this.studioFactory, this.tagFactory, this.coverFactory);
    Game game = gFactory.create();

    log.info("consumed : Game Created");
    log.info(
        "Game id :"
            + game.getId()
            + " Game name :"
            + game.getName()
            + " Game Studiot name "
            + game.getStudio().getName()
            + " Game Cover url"
            + game.getCover().getUrl());
  }
}
