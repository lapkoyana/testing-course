package ru.addressbook.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.addressbook.model.ContactData;

public class ContactDataGenerator {
	
	@Parameter(names = "-c", description = "Contact count")
	public int count;
	
	@Parameter(names = "-f", description = "Target file")
	public String file;
	
	@Parameter(names = "-d", description = "Data Format")
	public String format;
	
	public static void main(String[] args) throws IOException {
		ContactDataGenerator dataGenerator = new ContactDataGenerator();
		JCommander commander = new JCommander(dataGenerator);
		try {
			commander.parse(args);
		} catch (ParameterException e){
			commander.usage();
			return;
		}
		dataGenerator.run();
	}

	private void run() throws IOException {
		List<ContactData> contacts = generateContacts(count);
		if (format.equals("json")) {
			saveAsJson(contacts, new File(file));
		} else if (format.equals("csv")) {
			saveAsCsv(contacts, new File(file));
		} else {
			System.out.println("Unexpected format - " + format);
		}
	}

	private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(contacts);
		Writer writer = new FileWriter(file);
		writer.write(json);
		writer.close();
	}

	private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
		Writer writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(String.format("%s;%s\n", contact.getFirstName(),
					contact.getLastName(), contact.getPhoto().getPath()));
			contact.getPhoto().getCanonicalPath();
			contact.getPhoto().getAbsolutePath();
			contact.getPhoto().getPath();
		}
		writer.close();
	}

	private List<ContactData> generateContacts(int count) {
		List<ContactData> contacts = new ArrayList<ContactData>();
		for(int i = 1; i <= count; i++) {
			contacts.add(new ContactData().withFirstName(String.format("first name %s", i))
					.withLastName(String.format("last name %s", i)));
//i can't do this(
//					.withPhoto(new File(String.format("src/test/resources/romashka%s.jpg", i))));
		}
		return contacts;
	}
}
