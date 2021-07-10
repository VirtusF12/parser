import java.util.*;

/*
https://cadelta.ru/linux/id377
https://learn.javascript.ru/es-modern
https://www.w3schools.com/bootstrap4/bootstrap_ref_all_classes.asp


 http://www.cyberforum.ru/csharp-beginners/thread439112.html
 */

public class Main3Claster {

    public static void eql(String res1, String res2) {

        if (res1.length() > res2.length()) {

            String temp = res1;
            res1 = res2;
            res2 = temp;
        }


        int start = 0, end = 0, count = 0, si = 1, wi = 1;
        for ( si = 1; si < res1.length(); si++ ) {
            if (count > 1)
                break;

            String two1 = String.valueOf(res1.charAt(si - 1)) + String.valueOf(res1.charAt(si));

            for ( wi = 1; wi < res2.length(); wi++ ) {
                String two2 = String.valueOf(res2.charAt(wi - 1)) + String.valueOf(res2.charAt(wi));
                if ( two1.equals(two2) ){
                    System.out.println(two1 + ":" + two2);
                    start = si - 1;
                    int i;
                    for ( i = si; i < res1.length(); i++) {

                        String currTwo1 = String.valueOf(res1.charAt(i - 1)) + String.valueOf(res1.charAt(i));
                        String currTwo2 = String.valueOf(res2.charAt(i - 1)) + String.valueOf(res2.charAt(i));

                        if (!currTwo1.equals(currTwo2)) {
                            end = i - 1;
                            break;
                        } else  end = i;
                    }

                    si = i;
                    count = end - start + 1;
                    System.out.println("!!!start = " + start + ", end = " + end+"!!!");
                }
            }
        }

        double pr1 = (count)/(double)res1.length()*100;
        double pr2 = (count)/(double)res2.length()*100;
        double eql = ((pr1+pr2)/2);

        System.out.println(pr1);
        System.out.println(pr2);
        //if (eql > 50.0)
            System.out.println("("+res1 + ":" + res2 + ") com = " + eql + "%");
    }


//        // String text1 = "Распродажа остатков склада СТИЛЬНЫХ МУЖСКИХ ЧАСОВ СО СКИДКОЙ 50%";
//        String text1 = "Распродажа"; // СКИДКОЙ yскидкi99 слово
//        String res1 = text1.toLowerCase();
//        System.out.println("res1 = " + res1 + "\n\n");
//

//        // String text2 = "КАМЕРА скидк проДажа SPORTCAM FULLHD";
//        String text2 = "продажа"; // скидк rСКИДКОЙf886y корова
//        // System.out.println("text2 = " + text2 + "\n\n");
//        String res2 = text2.toLowerCase();
//        System.out.println("res2 = " + res2 + "\n\n");

    public static void main(String[] args) {

        String[] arrPostText = new String[7];
        arrPostText[0] = "Заказать триммер со скидкой \uD83D\uDC49 https://vk.cc/8uocjj\n" +
                "\n" +
                "✅ Укрепляет мышцы и пресс\n" +
                "⏱ Экономит кучу времени\n" +
                "✅ Гарантия 12 месяцев\n" +
                "✅ Без предоплат!\n" +
                "\n" +
                "\uD83D\uDE9B Быстрая доставка по \uD83C\uDDF7\uD83C\uDDFAРоссии, \uD83C\uDDF0\uD83C\uDDFFКазахстану, \uD83C\uDDFA\uD83C\uDDE6 Украине, \uD83C\uDDE7\uD83C\uDDFEБеларуси!";
        arrPostText[1] = "Распродажа остатков склада СТИЛЬНЫХ МУЖСКИХ ЧАСОВ\n" +
                "СО СКИДКОЙ 50%\n" +
                "\n" +
                "Заказать со триммера скидкой \uD83D\uDC49 https://vk.cc/8i6Vjt\n" +
                "\n" +
                "⚠ОПЛАТА ПРИ ПОЛУЧЕНИИ НА ПОЧТЕ⚠\n" +
                "✅ ЭЛИТНЫЙ БРЕНД\n" +
                "✅ СОВЕРШЕННЫЙ ДИЗАЙН\n" +
                "✅ МИРОВАЯ ПОПУЛЯРНОСТЬ\n" +
                "✅ ВЫСОКОЕ КАЧЕСТВО\n" +
                "\n" +
                "\uD83D\uDE9B Быстрая доставка по \uD83C\uDDF7\uD83C\uDDFAРоссии, \uD83C\uDDF0\uD83C\uDDFFКазахстану, \uD83C\uDDE7\uD83C\uDDFEБеларуси";
        arrPostText[2] = "5 часов на огороде, а я улыбаюсь! \uD83D\uDE0A\n" +
                "❌От лопаты и тяпки ломит спину, натираются мозоли, гудят ноги\uD83D\uDE12\n" +
                "- Ничего этого нет, если работаешь ручным культиватором \"Торнадо\".\n" +
                "Вот его сайт ⏩ https://vk.cc/84joYP\n" +
                "\n" +
                "в 2 раза быстрее, Акции чем с лопатой или тяпкой !может делать 6 видов работ ,на 80% снижает нагрузку на мышцы , больше ничего не болит на утро\n" +
                "\n" +
                "- Доставка почтой в любой город России! Оплата при получении\n" +
                "\uD83D\uDD25 СКИДКА 53% по этой ссылке ⏩ https://vk.cc/84joYP";
        arrPostText[3] = "Мужской триммер с 3-мя насадками Micro Touch Solo!!!\n" +
                "\n" +
                "\uD83D\uDC49 Идеальные контуры бороды\n" +
                "\uD83D\uDC49 Чёткая линия бакенбард\n" +
                "\uD83D\uDC49 Бритва может работать 45 минут от одного заряда\n" +
                "\uD83D\uDC49 Бреет за один проход\n" +
                "\n" +
                "Заказать со скидкой: https://vk.cc/84j3Ta";
        arrPostText[4] = "ЭКШН КАМЕРА SPORTCAM FULLHD\n" +
                "+ PowerBank на 10000mAh В ПОДАРОК!☝\uD83D\uDCA5\n" +
                "\uD83D\uDD25ЗАКАЖИ ПО АКЦИИ СЕЙЧАС \uD83D\uDC49 https://vk.cc/84iZP7\n" +
                "\n" +
                "✅Снимает на уровне GoPro\n" +
                "✅12 креплений в комплекте\n" +
                "✅Функция видеорегистратора\n" +
                "✅Аквабокс для съемки под водой\n" +
                "✅и еще много чего...\uD83D\uDC4D\uD83D\uDE09\n" +
                "Подробнее: https://vk.cc/84iZP7\n" +
                "\n" +
                "\uD83D\uDE9A Быстрая доставка по России.\n" +
                "\uD83D\uDCE6 Оплата ПОСЛЕ получения!\n" +
                "\n" +
                "\uD83D\uDD25ЗАКАЖИ ПО АКЦИИ СЕЙЧАС \uD83D\uDC49https://vk.cc/84iZP7";
        arrPostText[5] = "☑Часы ROLEX Daytona☑ \uD83D\uDD34Закажи со скидкой↪ http://newmagzin.kupiudachy-online.ru/JKpaN7/ \uD83D\uDC49Супер цена. \uD83D\uDC49Быстрая доставка по всей России. \uD83D\uDC49Оплата при получении. \uD83D\uDCF1После оформления заказа, вам позвонит наш оператор. ➕Часы ROLEX \"Daytona\" - абсолютное соотвествие оригиналу, вплоть до серийного номера и уникальной резьбы на задней крышке. \uD83D\uDD34Закажи со скидкой↪ http://newmagzin.kupiudachy-online.ru/JKpaN7/";
        arrPostText[6] = "☑Армейские часы Amst☑ \uD83D\uDD34Закажи со скидкой↪ http://newmagzin.kupiudachy-online.ru/HFNaY6/ \uD83D\uDC49Супер цена. \uD83D\uDC49Быстрая доставка по всей России. \uD83D\uDC49Оплата при получении. \uD83D\uDCF1После оформления заказа, вам позвонит наш оператор. ➕Оригинальные ударопрочные и водонепроницаемые армейские мужские часы AMST. \uD83D\uDD34Закажи со скидкой↪ http://newmagzin.kupiudachy-online.ru/HFNaY6/";

        for (int i = 0; i<arrPostText.length; i++)
            arrPostText[i] = arrPostText[i].toLowerCase();


        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Collections.addAll(set1, arrPostText[5].split(" "));
        Collections.addAll(set2, arrPostText[6].split(" "));

//        for (int i = 0; i < arr2.length; i++) {
//
//            for(int j = 0; j < arr1.length; j++) {
//                if (arr1[j].contains(arr2[i])){
//                    if (arr2[i].length()>2)
//                    // System.out.println(arr2[i]);
//                    set.add(arr2[i]);
//                }
//
//            }
//        }

        Set<String> set3 = new HashSet<>();


        Iterator iterator = set2.iterator();
        while (iterator.hasNext()) {

            Iterator iter = set1.iterator();
            while (iter.hasNext()) {
                String word1 = iterator.next().toString();
                if (word1.equals(iter.next().toString()))
                    set3.add(word1);
            }
        }



        Iterator iterator2 = set3.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());

        }

//        String s = "12312продажа11123423 41234 ";
//        String s1 = "распродажа";
//
//        if (s.length() < s1.length()) {
//            String temp = s;
//            s = s1;
//            s1 = temp;
//        }
//
//        boolean isContain = s.contains(s1);
//
//        if (isContain) System.out.println("yes");
//        else {
//            System.out.println("no ------------ ");
//
//
//            int i = 0;
//            while (s1.length() > 0 && !isContain) {
//
//                String sub = s1.substring(0,s1.length()-i);
//                System.out.println("sub = " + sub);
//                isContain = s.contains(sub);
//
//                if (isContain) {
//                    // System.out.println("yes");
//                    System.out.println("countSub = " + sub.length());
//                } else
//                {
//                    System.out.println("no");
//                    i++;
//                }
//            }

//             i = 0;
//            while (s1.length() > 0 && !isContain) {
//
//                String sub = s1.substring(i,s1.length());
//                System.out.println("sub = " + sub);
//                isContain = s.contains(sub);
//
//                if (isContain) {
//                    // System.out.println("yes");
//                    System.out.println("countSub = " + sub.length());
//                } else
//                {
//                    System.out.println("no");
//                    i++;
//                }
//            }
//
//        }




    }

    public static void show(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("\n\n");
    }

    private static boolean isDigit(String strNum){

        try {
            int num = Integer.parseInt(strNum);
            return true;
        } catch (Exception ex) {

            return false;
        }
    }
}


/*
public Iterator iterator()
public int size()
public boolean isEmpty()
public boolean contains(Object o)
public boolean add(Object o)
public boolean addAll(Collection c)
public Object[] toArray()
public boolean remove(Object o)
public boolean removeAll(Collection c)
public boolean retainAll(Collection c) - (retain — сохранить). Выполняет операцию "пересечение множеств".
public void clear()
public Object clone()
 */

//        for (int i = 0; i < res.split(" ").length; i++) {
//            if ((arrWord[i].length() == 1) || isDigit(arrWord[i]) || (arrWord[i].length() == 2)) {
//
//            } else  hashSet.add(arrWord[i]); // list.add(arrWord[i]);
//        }
//
//        Iterator<String> iterator = hashSet.iterator();
//
//        while(iterator.hasNext()) {
//            String currStr = iterator.next();
//            int frequency = 0;
//            for (int i = 0; i < arrWord.length; i++)
//                if (currStr.equals(arrWord[i]))
//                    frequency++;
//            map.put(currStr, frequency);
//        }
//
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println("(" + entry.getKey() + ") : ("
//                    + entry.getValue()+")");
//        }
//
//        int max = 0;
//        for (Integer i : map.values()) {
//            if (i > max)
//                max = i;
//        }
//        System.out.println("\n\n max="+max);



/*
 Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("б", "2");
        map.put("в", "3");
        map.put("г", "4");
        map.put("д", "5");
        map.put("е", "6");
        map.put("ё", "7");
        map.put("ж", "8");
        map.put("з", "9");
        map.put("и", "10");


        map.put("й", "11");
        map.put("к", "12");
        map.put("л", "13");
        map.put("м", "14");
        map.put("н", "15");
        map.put("о", "16");
        map.put("п", "17");
        map.put("р", "18");
        map.put("с", "19");
        map.put("т", "20");

        map.put("у", "21");
        map.put("ф", "22");
        map.put("х", "23");
        map.put("ц", "24");
        map.put("ч", "25");
        map.put("ш", "26");
        map.put("щ", "27");
        map.put("ъ", "28");
        map.put("ы", "29");
        map.put("ь", "30");

        map.put("э", "31");
        map.put("ю", "32");
        map.put("я", "33");
 */