package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by jt on 6/28/17.
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureReactiveRepository uomReactiveRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand,
                                    UnitOfMeasureReactiveRepository uomReactiveRepository) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        this.uomReactiveRepository = uomReactiveRepository;
    }

    @Override
    public Flux<UnitOfMeasureCommand> listAllUoms() {

        return uomReactiveRepository.findAll()
                .map(unitOfMeasureToUnitOfMeasureCommand::convert);

/*        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());*/
    }
}
