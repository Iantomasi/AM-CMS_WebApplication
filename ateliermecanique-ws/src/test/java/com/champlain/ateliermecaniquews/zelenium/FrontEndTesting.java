package com.champlain.ateliermecaniquews.zelenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@SpringBootTest
public class FrontEndTesting {

        @BeforeEach()
        void setUp() {
            WebDriverManager.chromedriver().setup();
             Configuration.browser = "chrome";
        }

        @Test
        public void getAllCustomerAccounts() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);
            $("p").shouldHave(text("CUSTOMER ACCOUNTS"));
        }

        @Test
        public void getCustomerAccountByCustomerId() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);

            String accountId = "cdff4g82-9e8h-7532-1qws-75321v5ar963";
            SelenideElement accountLink = $$("td").findBy(text(accountId));
            accountLink.shouldBe(visible).click();
            $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));
        }


        @Test
        public void updateCustomerAccountByCustomerId() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);
            String accountId = "yzab8cd5-3e6f-8796-2abi-96330c6bw164";
            SelenideElement accountLink = $$("td").findBy(text(accountId));
            accountLink.shouldBe(visible).click();
            $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

            $("input[name='firstName']").setValue("Jeff");
            $("input[name='lastName']").setValue("Doe");
            $("input[name='email']").setValue("john@example.com");
            $("input[name='phoneNumber']").setValue("123456789");
            $("button[type='submit']").click();
        }

        // MICHAEL 01
        @Test
        public void getAllVehiclesForCustomerAccount() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);
            String customerId = "lmno8p45-3q6r-8791-2abc-96325t5ar159";
            SelenideElement customerLink = $$("td").findBy(text(customerId));
            customerLink.shouldBe(visible).click();
            $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

            $("a[href^='/admin/customers/" + customerId + "/vehicles']").click();
            $("p").shouldHave(text("VEHICLES"));
        }


        // GIULIANO 02
        @Test
        public void getVehicleByVehicleId() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);

            String customerId = "aebc4d79-2b6f-4527-3zda-05432x5ar321";
            String vehicleId = "0f8c5e36-9e94-4c6d-921d-29d7e2e923b5";

            $$("td").findBy(text(customerId)).click();
            Selenide.sleep(2000);

            $("a[href^='/admin/customers/" + customerId + "/vehicles']").click();
            $("p").shouldHave(text("VEHICLES"));
            Selenide.sleep(2000);

            SelenideElement vehicleLink = $$("td").findBy(text(vehicleId));
            if (vehicleLink.exists()) {
                vehicleLink.click();
            } else {
                throw new NoSuchElementException("Vehicle link not found for ID: " + vehicleId);
            }

            Selenide.sleep(2000);
            $("p").shouldHave(text("VEHICLE DETAILS"));
        }

        // GIULIANO 03
        @Test
        public void updateVehicleByVehicleId() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);
            String customerId = "aebc4d79-2b6f-4527-3zda-05432x5ar321";
            String vehicleId = "0f8c5e36-9e94-4c6d-921d-29d7e2e923b5";

            SelenideElement customerLink = $$("td").findBy(text(customerId));
            customerLink.shouldBe(visible).click();
            Selenide.sleep(1000);

            $("a[href^='/admin/customers/" + customerId + "/vehicles']").click();
            $("p").shouldHave(text("VEHICLES"));
            Selenide.sleep(1000);

            SelenideElement vehicleLink = $$("td").findBy(text(vehicleId));
            if (vehicleLink.exists()) {
                vehicleLink.click();
            } else {
                throw new NoSuchElementException("Vehicle link not found for ID: " + vehicleId);
            }

            $("p").shouldHave(text("VEHICLE DETAILS"));

            $("input[name='make']").setValue("NewMake");
            $("input[name='model']").setValue("NewModel");
            $("input[name='year']").setValue("2023");

            $("select[name='transmission_type']").selectOption("Manual");
            $("input[name='mileage']").setValue("1000");

            $("button[type='submit']").click();
            Selenide.sleep(2000);
        }

        // CRISTIAN 02
        @Test
        public void deleteCustomerAccountByCustomerId(){
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);

            String accountId = "eggh9i83-7j8k-4567-4tyu-98765z5ar741";
            SelenideElement accountLink = $$("td").findBy(text(accountId));
            accountLink.shouldBe(visible).click();
            $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

            $$("button").findBy(text("Delete")).click();

            $(".bg-black").shouldBe(visible);
            $$("button").findBy(text("Yes")).click();

            switchTo().alert().accept();

            sleep(1000);
            $("p").shouldHave(text("CUSTOMER ACCOUNTS"));
        }

        // MICHAEL 02
        @Test
        public void addVehicleToCustomerAccount(){
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);
            String customerId = "lmno8p45-3q6r-8791-2abc-96325t5ar159";
            SelenideElement customerLink = $$("td").findBy(text(customerId));
            customerLink.shouldBe(visible).click();
            $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

            $("a[href^='/admin/customers/" + customerId + "/vehicles']").click();
            $("p").shouldHave(text("VEHICLES"));

            $$("button").findBy(text("add+")).click();
            sleep(1000);


            $("input[name='make']").setValue("Chevrolet");
            $("input[name='model']").setValue("Cruze");
            $("input[name='year']").setValue("2016");
            $("select[name='transmission_type']").selectOption("Manual");
            $("input[name='mileage']").setValue("50000");

            $("button[type='submit']").click();
            sleep(1000);

        }


        @Test
        public void deleteVehicleByVehicleId() {
            open("https://localhost:3000/");
            $("a[href='/login']").click();
            sleep(1000);
            $("button[type='submit']").click();
            sleep(1000);
            $("img[src='customersImage.svg']").click();
            sleep(1000);

            // ethan customer
            String customerId = "cdff4g82-9e8h-7532-1qws-75321v5ar963";
            String vehicleIdToDelete = "01aa1f26-9f9b-438e-8346-572c83c2f181";

            $$("td").findBy(text(customerId)).click();
            Selenide.sleep(2000);

            $("a[href^='/admin/customers/" + customerId + "/vehicles']").click();
            $("p").shouldHave(text("VEHICLES"));
            Selenide.sleep(2000);


            SelenideElement vehicleLink = $$("td").findBy(text(vehicleIdToDelete));
            if (vehicleLink.exists()) {
                vehicleLink.click();
            } else {
                throw new NoSuchElementException("Vehicle link not found for ID: " + vehicleIdToDelete);
            }

            $$("button").findBy(text("delete")).click();

            $(".bg-black").shouldBe(visible);
            $$("button").findBy(text("Yes")).click();

            sleep(1000);
            switchTo().alert().accept();

            $("p").shouldHave(text("VEHICLES"));
        }


    @Test
    public void getAllAppointments() {
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='appointments.svg']").click();
        sleep(1000);
        $("p").shouldHave(text("APPOINTMENTS"));
    }


    @Test
    public void updateAppointmentStatusAdmin(){
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='appointments.svg']").click();
        sleep(1000);
        $("p").shouldHave(text("APPOINTMENTS"));
        $$("button").findBy(text("CANCEL")).click();
        sleep(1000);
    }


    @Test
    public void getAllAppointmentsByCustomerId(){
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(5000);
        $("button[type='submit']").click();
        sleep(5000);
        $("img[src='customersImage.svg']").click();
        sleep(9000);
        String customerId = "yzab8cd5-3e6f-8796-2abi-96330c6bw164";
        SelenideElement customerLink = $$("td").findBy(text(customerId));
        customerLink.shouldBe(visible).click();
        sleep(5000);
        $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

        $("a[href^='/admin/customers/" + customerId + "/appointments']").click();
        $("p").shouldHave(text("APPOINTMENTS"));
        }

    @Test
    public void updateAppointmentStatusCustomer(){
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='customersImage.svg']").click();
        sleep(1000);
        String customerId = "yzab8cd5-3e6f-8796-2abi-96330c6bw164";
        SelenideElement customerLink = $$("td").findBy(text(customerId));
        customerLink.shouldBe(visible).click();
        $("p").shouldHave(text("CUSTOMER ACCOUNT DETAILS"));

        $("a[href^='/admin/customers/" + customerId + "/appointments']").click();
        $("p").shouldHave(text("APPOINTMENTS"));

        $$("button").findBy(text("CANCEL")).click();
        sleep(1000);

    }


    @Test
    public void getAppointmentByAppointmentId() {
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='appointments.svg']").click();
        sleep(1000);

        String appointmentId = "1008dc5c-d460-443f-8f37-a174284f8684";
        SelenideElement appointmentLink = $$("td").findBy(text(appointmentId));
        appointmentLink.shouldBe(visible).click();
        sleep(1000);

        $("p").shouldHave(text("APPOINTMENT DETAILS"));
    }

    @Test
    public void deleteAppointmentByAppointmentId() {
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='appointments.svg']").click();
        sleep(1000);

        String appointmentId = "1008dc5c-d460-443f-8f37-a174284f8682";
        SelenideElement appointmentLink = $$("td").findBy(text(appointmentId));
        appointmentLink.shouldBe(visible).click();
        sleep(1000);

        $$("button").findBy(text("delete")).click();

        $(".bg-black").shouldBe(visible);
        $$("button").findBy(text("Yes")).click();

        sleep(1000);
        switchTo().alert().accept();

        $("p").shouldHave(text("APPOINTMENTS"));
    }

    @Test
    public void deleteAppointmentByAppointmentIdCustomer() {
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='customersImage.svg']").click();
        sleep(1000);

        // mason rodriguez customer
        String customerId = "yzab8cd5-3e6f-8796-2abi-96330c6bw164";
        String appointmentIdToDelete = "1008dc5c-d460-443f-8f37-a174284f8683";

        $$("td").findBy(text(customerId)).click();
        Selenide.sleep(2000);

        $("a[href^='/admin/customers/" + customerId + "/appointments']").click();
        $("p").shouldHave(text("APPOINTMENTS"));
        Selenide.sleep(2000);


        SelenideElement appointmentLink = $$("td").findBy(text(appointmentIdToDelete));
        if (appointmentLink.exists()) {
            appointmentLink.click();
        } else {
            throw new NoSuchElementException("Appointment link not found for ID: " + appointmentIdToDelete);
        }

        $$("button").findBy(text("delete")).click();

        $(".bg-black").shouldBe(visible);
        $$("button").findBy(text("Yes")).click();

        sleep(1000);
        switchTo().alert().accept();

        $("p").shouldHave(text("APPOINTMENTS"));
    }


    @Test
    public void deleteAllCancelledAppointments() {
        open("https://localhost:3000/");
        $("a[href='/login']").click();
        sleep(1000);
        $("button[type='submit']").click();
        sleep(1000);
        $("img[src='appointments.svg']").click();
        sleep(1000);


        $$("button").findBy(text("Delete All Cancelled")).click();

        $$("button").findBy(text("Yes")).click();

        sleep(1000);
        switchTo().alert().accept();

        $("p").shouldHave(text("APPOINTMENTS"));
    }

    @Test
    public void addAppointmentToCustomerAccount(){
        open("https://localhost:3001/");
        $("a[href='/login']").click();
        sleep(2000);
        $("button[type='submit']").click();
        sleep(2000);
        $("img[src='appointments.svg']").click();
        sleep(2000);
        $$("button").findBy(text("Add")).click();
        sleep(2000);
        String timeToSelect = "09:00";
        $$("button").findBy(text(timeToSelect)).click();
        sleep(2000);
        String dateToSelect = "31";
        $$("h1").filterBy(text(dateToSelect)).first().click();
        sleep(2000);
        // Select a customer from the dropdown
        String customerName = "John Doe";
        SelenideElement customerDropdown = $("#customer-select");
        new Select(customerDropdown).selectByVisibleText(customerName);
        sleep(2000);

        String vehicleToSelect = "Cruze";
        SelenideElement vehicleDropdown = $("#vehicle-select");
        new Select(vehicleDropdown).selectByVisibleText(vehicleToSelect);
        sleep(2000);

        String servicesToSelect = "Air Conditioning";
        String buttonSelect = "Select";
        $$("button").findBy(text(buttonSelect)).click();
        sleep(2000);
        SelenideElement commentsTextarea = $("#comments");

        String commentText = "This is a test comment.";
        commentsTextarea.setValue(commentText);
        sleep(2000);

        String confirmButtonText = "Confirm";
        $$("button").findBy(text(confirmButtonText)).click();
        sleep(10000);

    }


}