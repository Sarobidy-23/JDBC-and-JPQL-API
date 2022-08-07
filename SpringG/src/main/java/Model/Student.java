package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Student {
    private String idStudent;
    private String name;
    private boolean active;


    @Override
    public String toString() {
        return idStudent+ name + active;
    }
}
