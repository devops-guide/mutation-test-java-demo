package unit;

import java.util.List;

public class DataObjectValidationService {

    private DependencyService service;

    public DataObjectValidationService(DependencyService service) {
        this.service = service;
    }

    public void validate(List<String[]> rawData) {
        for (String[] raw : rawData) {
            final DataObject dataObject = new DataObject(
                    service.getValueByRaw(raw[0]),
                    raw[1],
                    Double.valueOf(raw[2]),
                    Integer.valueOf(raw[3]));
            if (!dataObject.errorMessages().isEmpty()) {
                throw new RuntimeException(dataObject
                                                   .errorMessages()
                                                   .toString());
            }
        }
    }
}
