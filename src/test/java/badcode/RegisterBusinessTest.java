package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

    //Test to Fail

    @Test
    @DisplayName("สร้าง Register Class แต่ไม่กำหนดชื่อ ต้อง Fail")
    public void case01()
    {
        try {
            RegisterBusiness resgisterBusiness = new RegisterBusiness();
            resgisterBusiness.register(null,new Speaker());
            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("First name is required.")) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("สร้าง Speaker Class แต่ไม่กำหนดนามสกุล ต้อง Fail")
    public void case02()
    {

        try {
            RegisterBusiness resgisterBusiness = new RegisterBusiness();
            Speaker speaker = new Speaker();
            speaker.setFirstName("Satya");

            resgisterBusiness.register(null,speaker);
            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("Last name is required.")) {
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    @DisplayName("สร้าง Speaker Class แต่ไม่กำหนด Email ต้อง Fail")
    public void case03()
    {
        try {
            RegisterBusiness resgisterBusiness = new RegisterBusiness();
            Speaker speaker = new Speaker();
            speaker.setFirstName("Satya");
            speaker.setLastName("Amatdiratha");

            resgisterBusiness.register(null,speaker);

            fail();
        } catch (ArgumentNullException e) {
            if (!e.getMessage().equals("Email is required.")) {
                e.printStackTrace();
                fail();
            }
        }
    }
    @Test
    @DisplayName("Email ไม่ถูก format ต้อง Fail")
    public void case04()
    {
        try {
            RegisterBusiness resgisterBusiness = new RegisterBusiness();
            Speaker speaker = new Speaker();
            speaker.setFirstName("Satya");
            speaker.setLastName("Amatdiratha");
            speaker.setEmail("foo@bar.com");

            resgisterBusiness.register(null,speaker);

            fail();
        } catch (SpeakerDoesntMeetRequirementsException e) {
            if (!e.getMessage().equals("Speaker doesn't meet our standard rules.")) {
                e.printStackTrace();
                fail();
            }
        }
    }

    @Test
    @DisplayName("ไม่ได้สร้าง SpeakerRepository ต้อง Fail")
    public void case05()
    {
        try {
            RegisterBusiness resgisterBusiness = new RegisterBusiness();
            Speaker speaker = new Speaker();
            speaker.setFirstName("Satya");
            speaker.setLastName("Amatdiratha");
            speaker.setEmail("foo@gmail.com");

            resgisterBusiness.register(null,speaker);

            fail();
        } catch (SaveSpeakerException e) {
            if (!e.getMessage().equals("Can't save a speaker.")) {
                e.printStackTrace();
                fail();
            }
        }
    }
}