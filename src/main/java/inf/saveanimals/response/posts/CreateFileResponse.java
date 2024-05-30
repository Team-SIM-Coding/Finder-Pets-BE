package inf.saveanimals.response.posts;

import lombok.Data;

@Data
public class CreateFileResponse {


    private String savePath;

    public void updateItemImg( String savePath) {
        this.savePath = savePath;
    }

}