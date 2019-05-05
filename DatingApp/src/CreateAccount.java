public class CreateAccount {
    public boolean isValid (String firstName, String lastName, String email, String password, String confirmPassword,
                            String age, String gender, String sexuality)
            throws InvalidFirstNameException, InvalidLastNameException, InvalidAgeException {
        if (firstName.length() <= 2)
        {
            throw new InvalidFirstNameException();
        }
        if (lastName.length() <= 1)
        {
            throw new InvalidLastNameException();
        }
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException(0);
        }
        int intAge = Integer.parseInt(age);
        if (intAge <= 16)
        {
            throw new InvalidAgeException(intAge);
        }
        return false; // TODO: fix
    }
}
