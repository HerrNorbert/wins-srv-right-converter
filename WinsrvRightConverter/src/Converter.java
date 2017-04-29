import java.io.*;

public class Converter {
  private String inputName, outputName;
  public Converter(String fileName) {
    inputName = fileName;
    if(fileName.contains(".")){
      outputName = fileName.substring(0,fileName.indexOf('.')) + ".csv";
    }
    else{
      outputName = filename + ".csv";
    }
  }

  public void Convert(){
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
      String readedLine, writeLine, folder;
      boolean isUseFull=false;
      readedLine=writeLine=folder="";
      fileWriter = new FileWriter(outputName);
      bufferedWriter = new BufferedWriter(fileWriter);

      while ((readedLine = br.readLine()) != null) {
        if(readedLine.length()>1) {
          if(readedLine.contains("P")){
            writeLine = readedLine.substring(readedLine.indexOf(":")+1,readedLine.length());
            bufferedWriter.write(writeLine);
            bufferedWriter.newLine();
            folder=writeLine;
            writeLine="";
          }
          else if(readedLine.contains("A") && readedLine.charAt(3) == 'u'){
            isUseFull=false;
          }
          else if(readedLine.contains("A") && readedLine.charAt(3) == 'c'){
            isUseFull=true;
            if(readedLine.indexOf("\\") > 0 ){
              writeLine =folder+ ";" + readedLine.substring(readedLine.indexOf(":"),readedLine.indexOf(" ",readedLine.indexOf("\\")))
                      +";"+readedLine.substring(readedLine.indexOf(" ",readedLine.indexOf("\\")));
              bufferedWriter.write(writeLine);
              bufferedWriter.newLine();
            }
          }
          else if(isUseFull){
            if(readedLine.indexOf("\\") > 0 ) {
              writeLine = folder + ";" + readedLine.substring(readedLine.indexOf(":") + 1, readedLine.indexOf(" ", readedLine.indexOf("\\"))) + ";" + readedLine.substring(readedLine.indexOf(" ", readedLine.indexOf("\\")));//nev
              bufferedWriter.write(writeLine);
              bufferedWriter.newLine();
            }
          }
        }
      }bufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
