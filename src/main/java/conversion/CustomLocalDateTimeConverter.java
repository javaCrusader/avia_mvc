package conversion;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Converter(autoApply = true)
public class CustomLocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime entityValue) {
        return entityValue == null ? null : java.sql.Timestamp.valueOf(entityValue);
    }

    @Override
    public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {

        return databaseValue == null ? null : databaseValue.toLocalDateTime();
    }
}