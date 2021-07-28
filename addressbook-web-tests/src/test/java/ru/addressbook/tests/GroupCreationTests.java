package ru.addressbook.tests;

import org.testng.annotations.*;

import com.thoughtworks.xstream.XStream;

import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> validGroups() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
		String xml = "";
		String line = reader.readLine();
		while (line != null) {
			xml += line;
			line = reader.readLine();
		}
		XStream xStream = new XStream();
		xStream.processAnnotations(GroupData.class);
		List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
		return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
	}
	
	@Test(dataProvider = "validGroups")
	public void testGroupCreation(GroupData group){
		am.goTo().groups();
		Groups before = am.group().all();
		am.group().create(group);
		assertThat(am.group().count(), equalTo(before.size() + 1));
		Groups after = am.group().all();
		assertThat(after, equalTo(before.withAdded(
				group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
	}
	
	@Test
	public void testBadGroupCreation() throws Exception {
		am.goTo().groups();
		Groups before = am.group().all();
		GroupData group = new GroupData().withName("test1'");
		am.group().create(group);
		assertThat(am.group().count(), equalTo(before.size()));
		Groups after = am.group().all();
		assertThat(after, equalTo(before));
	}
}
