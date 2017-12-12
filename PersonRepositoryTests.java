package b;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.Config;
import model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class PersonRepositoryTests {

	@Autowired private MongoTemplate mongoTemplate;
	
	@Test
	public void test() {
		List<Person> list = mongoTemplate.find(null, Person.class);
		for (Person person : list) {
			System.out.println(person);
		}
		System.out.println("----------------------------");
	}
	
}
