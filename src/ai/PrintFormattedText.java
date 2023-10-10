package ai;

public class PrintFormattedText {
    public static void main(String[] args) {
        String longText = "Psychological Feedback\n" +
                "0 children\n" +
                "\n" +
                "\nA person who expresses a desire to have no children can be understood through a psychological " +
                "lens as an individual with a unique set of motivations, values, and considerations. " +
                "It's important to emphasize that this choice, like any other related to family planning, " +
                "is deeply personal and multifaceted, influenced by a complex interplay of psychological factors.\n" +
                "\n" +
                "\nFirst, individuals who opt not to have children may highly value their personal autonomy and independence. " +
                "They may prioritize the freedom to make choices without the responsibilities and commitments that come with parenthood. " +
                "This suggests a strong sense of self and a desire for self-fulfillment.\n" +
                "\n" +
                "\nSecond, some individuals may choose to forgo parenthood due to concerns about the environment. " +
                "They may be deeply committed to sustainability and believe that reducing the global population " +
                "is an ethical choice in the face of ecological challenges.\n" +
                "\n" +
                "\nThird, a desire to focus on one's career, personal growth, or self-actualization can also be a " +
                "driving force behind this decision. These individuals may have ambitious goals and view parenthood " +
                "as potentially conflicting with their professional or personal aspirations.\n" +
                "\n" +
                "\nFourth, past experiences, such as a difficult childhood or negative encounters with children, could " +
                "lead to a preference for a child-free life. This choice may be driven by a desire to avoid repeating " +
                "negative patterns or to prioritize personal healing.\n" +
                "\n" +
                "\nFive, economic factors may play a significant role. A person may choose not to have children due to " +
                "concerns about the cost of raising them, wanting to maintain financial stability, or wanting to enjoy " +
                "a certain lifestyle.\n" +
                "\n" +
                "\nSix, some individuals may not feel a strong innate desire to become parents. They might not experience " +
                "the same level of maternal or paternal instinct that others do.\n" +
                "\n" +
                "\nIn conclusion, the decision to have no children is a deeply personal one, influenced by a myriad of " +
                "psychological factors.";

        // Define the maximum line width for the console (adjust as needed)
        int maxLineWidth = 80;

        // Split the text into paragraphs and format each paragraph to fit within the console width
        String[] paragraphs = longText.split("\n\n");
        for (String paragraph : paragraphs) {
            String[] words = paragraph.split("\\s+");
            StringBuilder formattedParagraph = new StringBuilder();
            int lineLength = 0;

            for (String word : words) {
                if (lineLength + word.length() + 1 <= maxLineWidth) {
                    // Add the word to the current line
                    formattedParagraph.append(word).append(" ");
                    lineLength += word.length() + 1;
                } else {
                    // Start a new line with the word
                    formattedParagraph.append("\n").append(word).append(" ");
                    lineLength = word.length() + 1;
                }
            }

            // Print the formatted paragraph
            System.out.println(formattedParagraph.toString());
            System.out.println(); // Add an extra line break between paragraphs
        }
    }
}

