package CoreVkApiParse.WallDescript;

import java.util.List;

public class Wall {


    private String id, name, screen_name, is_closed;

    public Wall(String id, String name, String screen_name, String is_closed) {
        this.id = id;
        this.name = name;
        this.screen_name = screen_name;
        this.is_closed = is_closed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getIs_closed() {
        return is_closed;
    }



    /*
            "id": 37008294,
            "name": "Вечерний Ургант",
            "screen_name": "vecherniy.urgant",
            "is_closed": 0,
            "type": "page",
            "is_admin": 0,
            "is_member": 0,
            "description": "Вечернее развлекательное шоу на Первом канале. Талантливая команда во главе с ведущим Иваном Ургантом. В гостях самые яркие представители российского и мирового шоу-бизнеса, актуальные темы, последние новости и только живая музыка. "Вечерний Ургант" с понедельника по пятницу дарит всей стране хорошее настроение!",
            "photo_50": "https://pp.userap...1IOOFCVeM.jpg?ava=1",
            "photo_100": "https://pp.userap...VclFV_OsM.jpg?ava=1",
            "photo_200": "https://pp.userap...TB9mlcyTQ.jpg?ava=1"
   */
}
