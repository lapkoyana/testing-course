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

import ru.addressbook.model.ContactData;

public class ContactDataGenerator {
	
	@Parameter(names = "-c", description = "Contact count")
	public int count;
	
	@Parameter(names = "-f", description = "Target file")
	public String file;
	
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
		save(contacts, new File(file));		
	}

	private void save(List<ContactData> contacts, File file) throws IOException {
		Writer writer = new FileWriter(file);
		//I do not know how to generate a json or xml file
		for (ContactData contact : contacts) {
			writer.write(String.format("%s;%s\n", contact.getFirstName(),
					contact.getLastName()));
		}
		writer.close();
	}

	private List<ContactData> generateContacts(int count) {
		List<ContactData> contacts = new ArrayList<ContactData>();
		for(int i = 0; i < count; i++) {
			contacts.add(new ContactData().withFirstName(String.format("first name %s", i))
					.withLastName(String.format("last name %s", i)));
		}
		return contacts;
	}
}
