package net.dzale.alexa.concierge.model.repository;

import net.dzale.alexa.concierge.model.entity.AlexaConciergeUserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Access alexa-concierge user database values.
 *
 * @dzale
 */
public interface AlexaConciergeUserRepository extends CrudRepository<AlexaConciergeUserEntity, Long> {

    AlexaConciergeUserEntity findOneByUsername(String username);

    AlexaConciergeUserEntity findOneById(Long userId);

}
