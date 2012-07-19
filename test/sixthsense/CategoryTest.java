/**
 * Authors: Aroop Ganguly
 */
package sixthsense;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CategoryTest {
	@Test
	public void testCtorForNameMemberInit() {
		Category c = new Category("c1");
		if (!c.getName().equals("c1")) {
			fail("Category constructor Name value incorrect");
		}
	}
	
	@Test
	public void testParseName() {
		String testStr = "abc1121bcd";
		if (!Category.parseName(testStr).equals("abc")) {
			fail("ParseName is incorrect");
		}
	}
	
	@Test
	public void testName() {
		Category c = new Category("test");
		if (!c.getName().equals("test")) {
			fail("Name not retrived correctly");
		}
	}
	
	@Test
	public void testExample() {
		ArrayList<String> data = new ArrayList<String>(5);
		for (@SuppressWarnings("unused")
		String s : data) {
			s = "t";
		}
		Category c = null;
		try {
			c = new Category("test", data);
		}
		catch (Exception e) {
			if (e.getMessage().indexOf("Prototypes name") != -1) {
				return;
			}
		}
		if (!(c.getExamples() == data.size())) {
			fail("Example() not working properly");
		}
	}
	
	@Test
	public void testAddExample() {
		Category c = new Category("test");
		try {
			c.addExample(new Gesture("test1", null));
		}
		catch (Exception e) {
			if (e.getMessage().indexOf("Prototype name") != -1) {
				return;
			}
			else
				fail("error in adding examples." + e.getMessage());
		}
		if (c.getExamples() != 1) {
			fail("error in adding examples");
		}
	}
	
	@Test
	public void testCtorForNameAndObjectMembersInit() {
		Category c = null;
		try {
			Gesture g = new Gesture("c1", null);
			c = new Category("c1", g);
		}
		catch (Exception e) {
			if (e.getMessage().indexOf("Prototype name") != -1) {
				return;
			}
			else
				fail("failed to add example, " + e.getMessage());
		}
		if (c == null || c.getExamples() == 0) {
			fail("no examples added in constructor");
		}
		else if (c.getExamples() > 1) {
			fail("garbage values for examples inside Category");
		}
	}
	
	@Test
	public void testCtorForGenericParamsInit() {
		Category c = null;
		ArrayList<String> data = new ArrayList<String>(5);
		for (@SuppressWarnings("unused")
		String s : data) {
			s = "t";
		}
		try {
			c = new Category("c1", data);
		}
		catch (Exception e) {
			fail("failed to add example, " + e.getMessage());
		}
		if (c == null || c.getExamples() != data.size()) {
			fail("no examples added incorrectly in constructor");
		}
		else {
			for (int i = 0; i < 5; i++) {
				try {
					if(c.getGesture(i)==null)
						return;
					if (!c.getGesture(i).getClass().equals(Gesture.class)) {
						fail("Examples not added properly as Gestures in Contructor");
						break;
					}
				}
				catch (Exception e) {
					if (e.getMessage().indexOf("prototypes not initialised") != -1) {
						return;
					}
					else
						fail("Examples not added properly as Gestures in Contructor");
				}
			}
		}
	}
}
