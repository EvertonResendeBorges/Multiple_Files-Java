package multipleFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static int fileNumber = 1;

	private static void saveFile(String files) {
		String fileName = "file_" + fileNumber + ".txt";

		try {
			File file = new File(fileName);
			while (file.exists()) {
				fileNumber++;
				fileName = "file_" + fileNumber + ".txt";
				file = new File(fileName);
			}

			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(files);
			writer.close();

			System.out.println("File saved successfully in " + fileName);
			fileNumber++;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("An error occurred while saving the file.");
		}
	}

	private static void listFiles(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			StringBuilder file = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				file.append(line);
				file.append("\n");
			}

			System.out.println("File: " + fileName + ":\n" + file.toString());
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file. " + fileName);
		}
	}

	private static void listFiles() {
		File directory = new File(".");
		File[] files = directory.listFiles();

		System.out.println("All files:");
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file.getName());
			}
		}
	}

	private static void deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("File " + fileName + " successfully deleted.");
			} else {
				System.out.println("Unable to delete the file. " + fileName);
			}
		} else {
			System.out.println("The file " + fileName + " does not exist.");
		}
	}

	private static void deleteAllFiles() {
		File directory = new File(".");
		File[] files = directory.listFiles();

		for (File file : files) {
			if (file.isFile()) {
				if (file.delete()) {
					System.out.println("File " + file.getName() + " successfully deleted.");
				} else {
					System.out.println("Unable to delete the file " + file.getName());
				}
			}
		}
	}

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {

			String file;
			int option = -1;

			while (option != 0) {

				System.out.println("0- Exit");
				System.out.println("1- Create new file");
				System.out.println("2- Read file");
				System.out.println("3- Show all files");
				System.out.println("4- Delete file");
				System.out.println("5- Delete all files \n");

				while (!s.hasNextInt()) {
					System.out.println("Enter a valid option. \n");

					System.out.println("0- Exit");
					System.out.println("1- Create new file");
					System.out.println("2- Read file");
					System.out.println("3- Show all files");
					System.out.println("4- Delete file");
					System.out.println("5- Delete all files \n");

					s.next();
				}

				option = s.nextInt();
				s.nextLine();

				System.out.println("");

				switch (option) {

				case 0:
					System.out.println("Program Closed");
					break;

				case 1:

					System.out.println("New file:");
					file = s.nextLine();

					saveFile(file);

					System.out.println();

					break;

				case 2:

					System.out.print("Enter the name of the file you want to upload: ");
					String fileName = s.nextLine();
					listFiles(fileName);
					break;

				case 3:

					listFiles();

					break;

				case 4:

					System.out.print("Enter the name of the file you want to delete: ");
					fileName = s.nextLine();
					deleteFile(fileName);
					break;

				case 5:
					deleteAllFiles();
					break;

				default:

					System.out.println("Enter a valid option. \n");

				}
			}

		}

	}

}
