package henrotaym.env.factories;

import henrotaym.env.entities.Game;
import java.math.BigInteger;
import net.datafaker.Faker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class GameFactory extends EntityFactory<Game> {
  private StudioFactory studioFactory;
  private TagFactory tagFactory;
  private CoverFactory coverFactory;

  public GameFactory(
      Faker faker,
      JpaRepository<Game, BigInteger> repository,
      StudioFactory studioFactory,
      TagFactory tagFactory,
      CoverFactory coverFactory) {
    super(faker, repository);
    this.studioFactory = studioFactory;
    this.tagFactory = tagFactory;
    this.coverFactory = coverFactory;
  }

  @Override
  protected Game entity() {
    return new Game();
  }

  @Override
  protected void attributes(Game entity) {
    entity.setName(this.faker.videoGame().title());
  }

  @Override
  protected void relationships(Game entity) {
    if (entity.getStudio() == null) {
      entity.setStudio(this.studioFactory.create());
    }
    if (entity.getCover() == null) {
      entity.setCover(this.coverFactory.create());
    }
    if (entity.getTags() == null || entity.getTags().size() == 0) {
      entity.getTags().add(this.tagFactory.create());
    }
  }
}
