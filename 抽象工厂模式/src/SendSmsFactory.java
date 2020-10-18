public class SendSmsFactory implements Provider{
    @Override
    public Sender Produce() {
       return new SmsSender();
    }
}
