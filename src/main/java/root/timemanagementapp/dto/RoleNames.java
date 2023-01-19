package root.timemanagementapp.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleNames {
    public static final String DEV_ROLE = "DEV";
    public static final String MASTER_ROLE = "MASTER";

    public static List<String> getRoleNamesCollection() {
        return new ArrayList<>(Arrays.asList(DEV_ROLE, MASTER_ROLE));
    }

}
