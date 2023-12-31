package logic.userInfoProcessorAndAnalyzer;

import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;

public class UserInfoAnalyzer
{
    public String determineAgeCategory(int age) {
        if (age <= 4) {
            return "Baby";
        } else if (age >= 5 && age <= 12) {
            return "Child";
        } else if (age >= 13 && age <= 17) {
            return "Teenager";
        } else if (age >= 18 && age <= 65) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    public String findZodiacSign(MonthDay monthDay) {
        int month = monthDay.getMonthValue();
        int day = monthDay.getDayOfMonth();

        if ((month == 12 && day >= 22 && day <= 31) || (month == 1 && day >= 1 && day <= 19))
            return "Capricorn";
        else if ((month == 1 && day >= 20 && day <= 31) || (month == 2 && day >= 1 && day <= 17))
            return "Aquarius";
        else if ((month == 2 && day >= 18 && day <= 29) || (month == 3 && day >= 1 && day <= 19))
            return "Pisces";
        else if ((month == 3 && day >= 20 && day <= 31) || (month == 4 && day >= 1 && day <= 19))
            return "Aries";
        else if ((month == 4 && day >= 20 && day <= 30) || (month == 5 && day >= 1 && day <= 20))
            return "Taurus";
        else if ((month == 5 && day >= 21 && day <= 31) || (month == 6 && day >= 1 && day <= 20))
            return "Gemini";
        else if ((month == 6 && day >= 21 && day <= 30) || (month == 7 && day >= 1 && day <= 22))
            return "Cancer";
        else if ((month == 7 && day >= 23 && day <= 31) || (month == 8 && day >= 1 && day <= 22))
            return "Leo";
        else if ((month == 8 && day >= 23 && day <= 31) || (month == 9 && day >= 1 && day <= 22))
            return "Virgo";
        else if ((month == 9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 22))
            return "Libra";
        else if ((month == 10 && day >= 23 && day <= 31) || (month == 11 && day >= 1 && day <= 21))
            return "Scorpio";
        else if ((month == 11 && day >= 22 && day <= 30) || (month == 12 && day >= 1 && day <= 21))
            return "Sagittarius";
        return null;
    }

    public String providePsychologicalFeedback(int numberOfChildren) {
        String result = "Their preferred number of children is " + numberOfChildren + ". ";
        String feedback;
        if (numberOfChildren == 0) {
            result += "Choosing not to have children is a valid and personal choice. It's important to emphasize that this choice, like any other related to family planning, is deeply personal and multifaceted, influenced by a complex interplay of psychological factors. First, individuals who opt not to have children may highly value their personal autonomy and independence. They may prioritize the freedom to make choices without the responsibilities and commitments that come with parenthood. This suggests a strong sense of self and a desire for self-fulfillment. Second, some individuals may choose to forgo parenthood due to concerns about the environment. They may be deeply committed to sustainability and believe that reducing the global population is an ethical choice in the face of ecological challenges. Third, a desire to focus on one's career, personal growth, or self-actualization can also be a driving force behind this decision. These individuals may have ambitious goals and view parenthood as potentially conflicting with their professional or personal aspirations. Finally, economic factors may play a significant role. A person may choose not to have children due to concerns about the cost of raising them, wanting to maintain financial stability, or wanting to enjoy a certain lifestyle. In conclusion, the decision to have no children is a deeply personal one, influenced by a myriad of psychological factors. ";
        } else if (numberOfChildren >= 1 && numberOfChildren <= 3) {
            result += "A person expressing a desire to have one to three children presents a range of psychological motivations and considerations in their family planning decisions. Such preferences can be influenced by a combination of personal values, life experiences, and individual circumstances. Firstly, these individuals often prioritize a balanced approach to family life. They may see the benefits of having a moderate number of children, believing it allows them to maintain a manageable level of responsibility while still experiencing the joys and rewards of parenthood. This suggests a desire for a fulfilling family life without feeling overwhelmed. Secondly, the desire for a small to medium-sized family may stem from a strong focus on quality over quantity. These individuals may want to provide their children with ample attention, resources, and opportunities for personal growth. They recognize that having fewer children can allow for more personalized parenting and deeper connections with each child. Thirdly, psychological factors such as attachment style and childhood experiences can play a role in this decision. Those who experienced secure and nurturing attachments in their own upbringing may be more inclined to want to replicate these positive experiences in their own families. Conversely, individuals who had less positive or stable childhoods may choose a smaller family size to ensure a more secure and supportive environment for their children. Additionally, the consideration of economic stability often factors into this choice. Those who desire one to three children may want to ensure that they can provide for their children's material needs and offer them opportunities for education and extracurricular activities without undue financial strain.";
        } else {
            result += "Having more than 4 children is a significant responsibility and may require careful planning. This choice represents a strong commitment to a larger family size and can be influenced by various factors: Firstly, individuals who aspire to have more than four children often place a high value on the concept of a large, close-knit family. They may have grown up in similar family structures or witnessed the benefits of having numerous siblings, fostering a desire to replicate these experiences for their own offspring. This suggests a deep appreciation for the emotional support and camaraderie that can come from a large family. Secondly, religious or cultural beliefs may play a significant role in this decision. Some cultures and religions encourage larger families as a means of preserving traditions, passing down values, or fulfilling religious obligations. These individuals may view having many children as a form of spiritual fulfillment or cultural continuity. Thirdly, a desire to leave a lasting legacy could be a motivating factor. Those who wish to have more than four children may believe that expanding their family lineage is a way to make a meaningful and lasting impact on the world, ensuring their values and genes are carried forward through generations. Furthermore, personality traits such as extraversion and sociability may drive this choice. Individuals who thrive in social settings and enjoy the dynamics of a bustling household may naturally gravitate toward larger families. Economic considerations also come into play. While it may seem challenging to support a larger family, some individuals who desire more than four children may have devised financial plans and strategies to ensure their children's well-being, education, and opportunities.";
        }
        return result;
    }
}
