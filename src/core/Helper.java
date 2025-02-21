package core;

import javax.swing.*;

public class Helper {
    public static void setTheme()
    {
      for (UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels())
      {
          if (info.getClassName().equals("Nimbus"))
          {
              try {
                  UIManager.setLookAndFeel(info.getClassName());
              }catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e)
              {
                  e.printStackTrace();


              }
          }
      }
    }


    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
      // Field içerisinini bos mu oldugunu kontrol edıyor
    }
    public static boolean isFieldListEmpty(JTextField[] fields)
    {for (JTextField field:fields)
    {
        if (isFieldEmpty(field)) return true ;
    }

        return false;
    }

    public static boolean isEmailValid(String mail)
    {optionPaneDialogTR();
        if (mail==null|mail.trim().isEmpty()) return false;
        if (!mail.contains("@")) return false;
        String[] parts=mail.split("@");
        if (parts.length !=2) return false;
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) return false;
        if (!parts [1].contains(".")) return false;

        return true;
    }
public static void optionPaneDialogTR(){
        UIManager.put("OptionPane.okButtonText","TAMAM");
}
    public static void showMsg(String message){
        String msg;
        String title;
        optionPaneDialogTR();
        switch (message)
        {
            case "fill"->
            {
                msg="TÜM ALANLARI DOLDURUN!";
                title="HATA";

            }
            case "mail"->
            {
                msg="mail ALANLARI DOLDURUN!";
                title="hata";

            }
            case "done"->
            {
                msg="İŞLEMLER BAŞARILI!";
                title="SONUÇ";

            }
            case "error"->
            {
                msg="BİR HATA OLUŞTU!";
                title="HATA";

            }
            default->
            {
               msg= message;
                title="mesaj";

            }
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }

}
