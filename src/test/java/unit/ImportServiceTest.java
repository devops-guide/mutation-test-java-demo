package unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ImportServiceTest {

    @InjectMocks
    private ImportService importService;
    @Spy
    private Repository<DataObject> repository;
    @Mock
    private DataObjectValidationService validationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_save_data_successfully() {

        List<String[]> rows = Arrays.asList(new String[]{"column1", "column2", "9", "99"},
                                            new String[]{"column11", "9.99", "9", "99"});
        doNothing().when(validationService).validate(rows);

        importService.batchImport(rows);

        verify(repository, times(1))
                .saveAll(argThat((List<DataObject> entities) -> {
                    DataObject first = entities.get(0);
                    DataObject second = entities.get(1);
                    return entities.size() == 2
                            && first.getColumn1().equals("column1")
                            && first.getColumn2().equals("column2")
                            && first.getColumn3().equals(9D)
                            && first.getColumn4().equals(99)
                            && second.getColumn1().equals("column11")
                            && second.getColumn2().equals("9.99")
                            && second.getColumn3().equals(9D)
                            && second.getColumn4().equals(99)
                            ;
                }));
    }

}
