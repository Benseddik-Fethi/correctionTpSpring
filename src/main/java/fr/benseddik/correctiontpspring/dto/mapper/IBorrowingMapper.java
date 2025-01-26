package fr.benseddik.correctiontpspring.dto.mapper;

import fr.benseddik.correctiontpspring.domain.Borrowing;
import fr.benseddik.correctiontpspring.dto.BorrowingResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, IBookMapper.class})
public interface IBorrowingMapper {
    Borrowing borrowingResponseToEntity(BorrowingResponse borrowingResponse);

    BorrowingResponse entityToborrowingResponse(Borrowing borrowing);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Borrowing partialUpdate(BorrowingResponse borrowingResponse, @MappingTarget Borrowing borrowing);
}