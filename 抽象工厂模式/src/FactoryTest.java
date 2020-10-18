public class FactoryTest {
    public static void main(String[] args) {
      Provider provider=new SendSmsFactory();
      Sender sender=provider.Produce();
      sender.Send();
    }
}
