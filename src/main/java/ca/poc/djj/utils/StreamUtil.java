package ca.poc.djj.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StreamUtil
{

  public static boolean checkElementsTextMatches(final List<WebElement> list, final String status) {
    return getTextFromElements(list).stream().allMatch(text -> text.equals(status));
  }

  public static List<String> getTextFromElements(final List<? extends WebElement> list) {
    return list.stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public static boolean areCheckboxesChecked(final List<? extends WebElement> list, final String shouldBeChecked) {
    return list.stream().map(element -> element.findElement(By.xpath(".//input")))
        .peek(elem -> System.out.println("Element was found with text = " + elem.getText()))
        .allMatch(elem -> elem.getAttribute("aria-checked").equals(shouldBeChecked));

  }

  public static int getCurrentDateIndexFromList(final List<? extends WebElement> list) {
    return IntStream.range(0, list.size()).filter(index -> list.get(index).getAttribute("class").contains("datePickerDayIsValue"))
        .findFirst().getAsInt();
  }
}
