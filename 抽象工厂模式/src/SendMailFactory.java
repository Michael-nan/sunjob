public class SendMailFactory implements Provider {
    @Override
    public Sender Produce() {
        return new MailSender();
    }
}
