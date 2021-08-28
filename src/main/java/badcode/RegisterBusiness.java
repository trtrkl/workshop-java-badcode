package badcode;

import java.util.Arrays;

public class RegisterBusiness {

    public Integer register(SpeakerRepository repository, Speaker speaker) {
        Integer speakerId;
        String[] validDomains = {"gmail.com", "live.com"};

        if (speaker.getFirstName() == null || speaker.getFirstName().trim().equals(""))
            throw new ArgumentNullException("First name is required.");
        if (speaker.getLastName() == null || speaker.getLastName().trim().equals(""))
            throw new ArgumentNullException("Last name is required.");
        if (speaker.getEmail() == null || speaker.getEmail().trim().equals(""))
            throw new ArgumentNullException("Email is required.");

        // Your Tasks ...
        String emailDomain = getEmailDomain(speaker.getEmail()); // Avoid ArrayIndexOutOfBound
        if (!Arrays.asList(validDomains).contains(emailDomain))
            throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our standard rules.");

        try {
            int exp = speaker.getExp();
            speaker.setRegistrationFee(getFee(exp));
            speakerId = repository.saveSpeaker(speaker);
        } catch (Exception exception) {
            throw new SaveSpeakerException("Can't save a speaker.");
        }

        return speakerId;
    }

    int getFee(int experienceYear) {
        int fee = 0;
        if (experienceYear <= 1) {
            fee = 500;
        } else if (experienceYear <= 3) {
            fee = 250;
        } else if (experienceYear <= 5) {
            fee = 100;
        } else if (experienceYear <= 9) {
            fee = 50;
        }
        return fee;
    }

    public String getEmailDomain(String email) {
        String[] inputs = email.trim().split("@");
        if (inputs.length == 2) return inputs[1];
        throw new DomainEmailInvalidException();
    }

}
