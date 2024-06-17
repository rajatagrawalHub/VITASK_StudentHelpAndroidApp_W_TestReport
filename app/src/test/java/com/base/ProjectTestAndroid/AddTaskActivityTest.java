package com.base.javaproj;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AddTaskActivityTest {
    @ParameterizedTest
    @ValueSource(strings ={

            "Learn coding in hands-on workshop.","Suitable for all levels.","Relax with yoga. Mats provided.", "Discover marketing trends.", "Expand network.",
            "Watch chefs, learn cooking.", "Personal finance insight.", "Improve photography.", "Develop leadership skills.", "Energetic dance workshop.", "Holistic health practices.", "Discuss environmental solutions.", "Enhance career prospects.", "Live music.", "Showcase science.", "Guided meditation.", "Entrepreneur insights.", "Fun art workshop. Materials provided.", "Language practice.", "Creative crafts.", "Tech talks.", "Authentic Italian cooking.", "Financial advice.", "Writing workshop.", "Documentaries.", "Bootcamp fitness."

    })
    public void testVerifyDescriptionValid(String desc){
        assertTrue(AddTask.verify_description(desc));
    }

    @ParameterizedTest
    @ValueSource(strings ={
            "Learn coding fundamentals in a hands-on workshop. Suitable for beginners and intermediate learners.", "Discover the latest marketing trends and strategies. Ideal for marketing professionals and entrepreneurs.",
            "Explore your creativity in a fun art workshop. All materials provided.", "Relax your mind and body with yoga. Suitable for all levels. Mats provided.", "Expand your professional network in a relaxed atmosphere. Bring business cards.", "Watch expert chefs prepare delicious dishes and learn cooking tips.", "Understand personal finance essentials and investment strategies.", "Explore scenic spots and improve your photography skills. Bring your camera.", "Develop leadership skills and strategies for professional growth.", "Learn dance moves and routines in a fun and energetic workshop.", "Explore holistic health practices for physical and mental well-being.", "Discuss environmental challenges and solutions with experts.", "Enhance your career prospects with valuable skills and insights.", "Enjoy live performances by talented musicians in a concert setting.", "Showcase scientific experiments and discoveries in an interactive fair.", "Practice mindfulness and relaxation techniques in a guided meditation session.", "Hear from successful entrepreneurs sharing their experiences and insights.", "Practice speaking foreign languages in a friendly and supportive environment.", "Get creative and make your own crafts in a hands-on workshop.", "Explore emerging technologies and industry trends in a series of tech talks.", "Learn to cook authentic Italian dishes from scratch with a professional chef.", "Get practical advice on budgeting, saving, and investing for financial security.", "Unleash your imagination and improve your writing skills in a creative writing workshop.", "Watch thought-provoking documentaries on various social and environmental issues.", "Get fit and strong with intense workouts in a bootcamp-style fitness class."


    })
    public void testVerifyDescriptionInvalid(String desc){
        assertFalse(AddTask.verify_description(desc));
    }

    @ParameterizedTest
    @ValueSource(strings ={

            "15-05-24", "20-06-24", "25-07-24", "30-08-24", "05-09-24", "10-10-24", "15-11-24", "20-12-24", "25-01-25", "01-02-25", "08-03-25", "15-04-25", "20-05-25", "25-06-25", "30-07-25", "05-08-25", "10-09-25", "15-10-25", "20-11-25", "25-12-25", "05-01-26", "10-02-26", "15-03-26", "20-04-26", "25-05-26"

    })
    public void testVerifyDateValid(String date){
        assertTrue(AddTask.verify_Date(date));
    }
    @ParameterizedTest
    @ValueSource(strings ={

            "Apr 15 24", "20-May-24", "25th June 2024", "July 30th, 2024", "10/August/24", "September 5, 2024", "20-Oct-2024", "Nov 15th, 2024", "December 1, '24", "25-January-24", "Feb 10, 2024", "March 20, 24", "04/05/2024", "15th May 2024", "30-Jun-24", "20/July/24", "10/Aug/2024", "Sept 25, '24", "Nov 15, '24", "01/Dec/2024", "20-Jan-25", "February 10, 2025", "05-Mar-2025", "20/April/25"

    })
    public void testVerifyDateInvalid(String date){
        assertFalse(AddTask.verify_Date(date));
    }



    @ParameterizedTest
    @ValueSource(strings ={
            "09:00", "17:30", "10:00", "14:00", "18:00", "11:00", "15:00", "08:30", "14:00", "16:30", "09:30", "13:00", "10:00", "19:00", "10:00", "17:00", "15:00", "18:30", "14:00", "11:00", "16:00", "09:30", "13:00", "19:30", "07:00"

    })
    public void testVerifytimeValid(String time){
        assertTrue(AddTask.verify_Time(time));
    }



    @ParameterizedTest
    @ValueSource(strings ={
            "10 AM", "9AM", "1PM", "11AM", "1245 PM", "815 AM", "200 PM", "1045 AM", "3.30 PM", "1130 AM", "9", "1200 PM", "10'30 AM", "2.45 PM", "8[00 AM", "1''00 PM", "11.15 AM", "90 AM", "120 PM", "3PM", "10AM", "30 PM", "85 AM", "115 AM", "90 AM"

    })
    public void testVerifytimeInvalid(String time){
        assertFalse(AddTask.verify_Time(time));
    }

}