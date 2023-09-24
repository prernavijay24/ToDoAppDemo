package commonLibs.utils;

import java.io.FileReader;

import java.io.IOException;

import com.opencsv.CSVReader;

public class CSVUtils {

	String currentWorkingDirectory = System.getProperty("user.dir");

	public static String browserType;

	public static String toDoExpectedResult;

	public static String addTaskExpectedResult;

	public static String firstTaskName;

	public static String secondTaskName;

	public static String thirdTaskName;

	public static String updateToDoExpecteResult;

	public static String updatedTitle;

	public static String selectCompleteValue;

	public static String baseURL;

	FileReader fileReader;

	CSVReader csvReader;

	public CSVUtils() throws IOException {

		String CSV_PATH = currentWorkingDirectory.concat("\\csvFile\\TestData.csv");

		fileReader = new FileReader(CSV_PATH);

		csvReader = new CSVReader(fileReader);

		String[] csvCell;

		// while loop will be executed till the last line In CSV.
		while ((csvCell = csvReader.readNext()) != null) {

			for (int i = 0; i < 1; i++) {

				browserType = csvCell[i];
				baseURL = csvCell[i + 1];
				toDoExpectedResult = csvCell[i + 2];
				addTaskExpectedResult = csvCell[i + 3];
				firstTaskName = csvCell[i + 4];
				secondTaskName = csvCell[i + 5];
				thirdTaskName = csvCell[i + 6];
				updateToDoExpecteResult = csvCell[i + 7];
				updatedTitle = csvCell[i + 8];
				selectCompleteValue = csvCell[i + 9];

			}

		}

	}
}
