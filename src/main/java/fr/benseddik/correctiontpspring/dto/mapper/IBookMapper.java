package fr.benseddik.correctiontpspring.dto.mapper;

import fr.benseddik.correctiontpspring.domain.Book;
import fr.benseddik.correctiontpspring.dto.BookResponse;
import fr.benseddik.correctiontpspring.dto.BookRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBookMapper {
    Book bookResponseToBook(BookResponse bookResponse);

    @Mapping(source = "available", target = "isAvailable")
    BookResponse toBookResponse(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookResponse bookResponse, @MappingTarget Book book);

    Book bookRequestToEntity(BookRequest bookRequest);

    BookRequest bookToBookRequest(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookRequest bookRequest, @MappingTarget Book book);
}