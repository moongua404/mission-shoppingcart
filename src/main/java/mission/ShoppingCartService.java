package mission;


import java.util.List;
import java.util.stream.Stream;
import mission.application.domain.enums.MessageConstant;
import mission.application.port.inport.Input;
import mission.application.port.inport.LectureDatabase;
import mission.application.port.outport.Logger;

public class ShoppingCartService {
    private static final ShoppingCartService instance = new ShoppingCartService();

    private final Logger logger;
    private final Input input;
    private final LectureDatabase lectureDatabase;

    private ShoppingCartService() {
        Config config = new Config();
        logger = config.getLogger();
        input = config.getInput();
        lectureDatabase = config.getLectureDatabase();
    }

    public static ShoppingCartService getInstance() {
        return instance;
    }

    public void run() {
        logger.print(MessageConstant.INPUT_TOTAL_BUDGET_GUIDE);
        int budget = input.getInt();
        validateBudget(budget);

        logger.print(MessageConstant.INPUT_LECTURE_ID_GUIDE);
        String lecturesIdLine = input.getString();
        List<Integer> lecturesIds = parseLecturesIdLine(lecturesIdLine);

        int price = calculatePrice(lecturesIds);
        if (price <= budget) {
            logger.print(MessageConstant.OUTPUT_BUDGET_FIT_MESSAGE);
        }
        logger.print(MessageConstant.OUTPUT_BUDGET_EXCESS_MESSAGE, price - budget);
    }

    private void validateBudget(int budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Invalid budget");
        }
    }

    private List<Integer> parseLecturesIdLine(String lectureIdLine) {
        return Stream.of(lectureIdLine.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private int calculatePrice(List<Integer> lecturesIds) {
        return lecturesIds.stream()
                .map(id -> lectureDatabase.findLecture(id).getCost())
                .reduce(0, Integer::sum);
    }
}
