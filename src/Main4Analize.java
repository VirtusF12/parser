

import CoreCluster.Cluster;
import CoreCluster.TreeCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main4Analize {

    public static void main(String[] args) {

        String[] arrPostText = new String[5];
        arrPostText[0] = "Распродажа остатков склада СТИЛЬНЫХ МУЖСКИХ ЧАСОВ\n" +
                "СО СКИДКОЙ 50%\n" +
                "\n" +
                "Заказать со скидкой \uD83D\uDC49 https://vk.cc/8i6Vjt\n" +
                "\n" +
                "⚠ОПЛАТА ПРИ ПОЛУЧЕНИИ НА ПОЧТЕ⚠\n" +
                "✅ ЭЛИТНЫЙ БРЕНД\n" +
                "✅ СОВЕРШЕННЫЙ ДИЗАЙН\n" +
                "✅ МИРОВАЯ ПОПУЛЯРНОСТЬ\n" +
                "✅ ВЫСОКОЕ КАЧЕСТВО\n" +
                "\n" +
                "\uD83D\uDE9B Быстрая доставка по \uD83C\uDDF7\uD83C\uDDFAРоссии, \uD83C\uDDF0\uD83C\uDDFFКазахстану, \uD83C\uDDE7\uD83C\uDDFEБеларуси";

        arrPostText[1] = "Заказать со скидкой  https://vk.cc/8uocjj\n" +
                "\n" +
                " Укрепляет мышцы и пресс\n" +
                " Экономит кучу времени\n" +
                " Гарантия 12 месяцев\n" +
                " Без предоплат!\n" +
                "\n" +
                " Быстрая доставка по России, Казахстану,  Украине, Беларуси!";

        arrPostText[2] = "5 часов на огороде, а я улыбаюсь! \n" +
                "От лопаты и тяпки ломит спину, натираются мозоли, гудят ноги\n" +
                "- Ничего этого нет, если работаешь ручным культиватором \"Торнадо\".\n" +
                "Вот его сайт  https://vk.cc/84joYP\n" +
                "\n" +
                "в 2 раза быстрее, чем с лопатой или тяпкой !может делать 6 видов работ ,на 80% снижает нагрузку на мышцы , больше ничего не болит на утро\n" +
                "\n" +
                "- Доставка почтой в любой город России! Оплата при получении\n" +
                " СКИДКА 53% по этой ссылке  https://vk.cc/84joYP";

        arrPostText[3] = "Мужской триммер с 3-мя насадками Micro Touch Solo!!!\n" +
                "\n" +
                " Идеальные контуры бороды\n" +
                " Чёткая линия бакенбард\n" +
                " Бритва может работать 45 минут от одного заряда\n" +
                " Бреет за один проход\n" +
                "\n" +
                "Заказать со скидкой: https://vk.cc/84j3Ta";

        arrPostText[4] = "ЭКШН КАМЕРА SPORTCAM FULLHD\n" +
                "+ PowerBank на 10000mAh В ПОДАРОК!\n" +
                "ЗАКАЖИ ПО АКЦИИ СЕЙЧАС  https://vk.cc/84iZP7\n" +
                "\n" +
                "Снимает на уровне GoPro\n" +
                "12 креплений в комплекте\n" +
                "Функция видеорегистратора\n" +
                "Аквабокс для съемки под водой\n" +
                "и еще много чего...\n" +
                "Подробнее: https://vk.cc/84iZP7\n" +
                "\n" +
                " Быстрая доставка по России.\n" +
                " Оплата ПОСЛЕ получения!\n" +
                "\n" +
                "ЗАКАЖИ ПО АКЦИИ СЕЙЧАС https://vk.cc/84iZP7";


        TreeCluster treeCluster = new TreeCluster("TreeAdsPosts");
        Pattern pattern = Pattern.compile("[а-ю]+");



        int j = 1, k = 0, size = 4; // выполнение сравнения постов друг с другом
        for (j = 1; j<=size; j++) {
            if (k < size-2) {
                if (j == size) {
                    k++;
                    j = k+1;
                }
                System.out.println("("+k+"):("+j+")");
                // сравнение постов
                String[] arr = getArr(arrPostText[k]);
                List<String> list = getList(pattern, arr);
                /////////////////////////////////////////
                String[] arr1 = getArr( arrPostText[j]);
                List<String> list1 = getList(pattern, arr1);

                Cluster cluster = new Cluster();
                for (int i = 0; i < list.size(); i++) {

                    String curr = list.get(i);

                    for (int t = 0; t < list1.size(); t++) {
                        if(curr.contains(list1.get(t))) {
                            //System.out.println(curr);
                            cluster.addMask(curr);
                        }
                    }
                }
                if (!cluster.getTextMask().equals("")) {
                    cluster.addPost(arrPostText[k]);
                    cluster.addPost(arrPostText[j]);
                    System.out.println("создан новый кластер");
                    treeCluster.addCluster(cluster);
                } else {
                    System.out.println("кластер не может быть создан");
                }

                System.out.println(cluster.getTextMask());
                // end
            } else {
                j = size+1;
            }
        }

        for (int i = 0; i < treeCluster.countClusters(); i++) {
            Cluster temp = treeCluster.getClusterIndex(i);
            temp.showAllPosts();
        }

    }

    public static ArrayList<String> getList(Pattern pattern, String[] arr) {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i++ ) {

            if (arr[i].length() > 3) {
                Matcher matcher = pattern.matcher(arr[i]);
                if (matcher.find()) {
                    //System.out.println("("+arr[i]+"):(start="+matcher.start()+",end="+(matcher.end()-1)+") sub="+arr[i].substring(matcher.start(),matcher.end()));
                    String word = arr[i].substring(matcher.start(),matcher.end());

                    if (!list.contains(word))
                        list.add(word);
                }
            }
        }
        return list;
    }

    public static String[] getArr(String textPost) {
        String ne = textPost.replace("\n", " ");
        String textLower = ne.toLowerCase();
        String[] arr = textLower.split(" ");
        return arr;
    }
}


//  Pattern pattern = Pattern.compile("[а-ю]+(ть|бил|ут|дел|ся|ли|ал|лала|дет|жит|жи|ила)");

//        String ne = arrPostText[0].replace("\n", " ");
//        String textLower = ne.toLowerCase();
//        String[] arr = textLower.split(" ");

//
//for(int i = 0; i < arr.length; i++ ) {
//
//        if (arr[i].length() > 3) {
//        Matcher matcher = pattern.matcher(arr[i]);
//        if (matcher.find()) {
//        //System.out.println("("+arr[i]+"):(start="+matcher.start()+",end="+(matcher.end()-1)+") sub="+arr[i].substring(matcher.start(),matcher.end()));
//        String word = arr[i].substring(matcher.start(),matcher.end());
//        list.add(word);
//        }
//        }
//        }


//System.out.println("---------------------------------");
//        // Pattern pattern = Pattern.compile("[а-ю]+");
//        for(int i = 0; i < arr1.length; i++ ) {
//
//        if (arr1[i].length() > 3) {
//        Matcher matcher = pattern.matcher(arr1[i]);
//        if (matcher.find()) {
//        //System.out.println("("+arr1[i]+"):(start="+matcher.start()+",end="+(matcher.end()-1)+") sub="+arr1[i].substring(matcher.start(),matcher.end()));
//        String word = arr1[i].substring(matcher.start(),matcher.end());
//        list1.add(word);
//        }
//        }
////        }
//
//    String text = "224привет";
//        if (text.contains("4")) System.out.println("YES");
//                else System.out.println("NO");