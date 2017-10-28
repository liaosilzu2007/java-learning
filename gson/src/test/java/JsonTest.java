import com.ddcx.json.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaosi on 2017/10/28.
 */
public class JsonTest {

    public static void main(String[] args) {

        Staff staff = createDummyObject();

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(staff);
        System.out.println(json);

    }

    private static Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition("Founder");
        staff.setSalary(new BigDecimal("10000"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("shell");

        staff.setSkills(skills);

        return staff;

    }

}
