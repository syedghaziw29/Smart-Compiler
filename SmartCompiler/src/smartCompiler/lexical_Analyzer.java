
package smartCompiler;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class lexical_Analyzer
{
      static ArrayList<Tokens> list2 = new ArrayList<>();
       
       
       String classpart="",valuepart="",linenumber="",token=null;
       ArrayList<String> tokenlist = new ArrayList<>();
       Pattern P  = Pattern.compile("[+-]?[0-9]+"); //Int
       
       Pattern P1 = Pattern.compile("[+-]?[0-9]{0,}\\.[0-9]{0,}([eE][+-]?[0-9]{0,})?"); //Float
       Pattern P2 = Pattern.compile("\\'(\\w|\\W)\\'|\\'(\\\\[n])\\'|\\'(\\\\[r])\\'"); //Char
       Pattern P3 = Pattern.compile("\".*\""); //String
       Pattern P4 = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]{0,30}"); //ID
       
       
       void TokenGenerator(String word,int lineno)
       {
           String temp2=null;
           
           
           if (token==null)
           {
               
               token=Endmarker(word, lineno);
               temp2=token;
               
                       if(temp2==null)
                       {
                
                           token=Integer(word,lineno);
                
                           temp2=token;
           
                    
                    if(temp2==null)
                    {
                        token=IsKeywords(word,lineno);
                        temp2=token;
                
                        
                        if(temp2==null)
                        {
                            token=IsOperator(word,lineno);
                            temp2=token;
                            
                            if(temp2==null)
                            {
                                token=IsPunctuator(word,lineno);
                                temp2=token;
                                
                                if(temp2==null)
                                {
                                    token=Characters_const(word,lineno);
                                    temp2=token;
                                    
                                    if(temp2==null)
                                    {
                                        token=Float_Const(word,lineno);
                                        temp2=token;
                                        
                                        if(temp2==null)
                                                {
                                                    token=String_Const(word,lineno);
                                                    temp2=token;
                                                       
                                                    if(temp2==null)
                                                        {
                                                            token=Identifiers(word,lineno);
                                                            temp2=token;
                
                                                        }   
                                   
                                                }   
                                    }   
                                }   
                            }   
                        }   
                    }
                }
            
           }
 
          
           
           if(token==null)
           {
               
                tokenlist.add("invalid lexeme"+","+word+","+lineno);
                
                Tokens t = new Tokens("invalid lexeme",word,lineno);
                list2.add(t);
                
                
                token=null;
           }
           else
           {
               tokenlist.add(token);
               token=null;
               
               
               //add token to list
           }
                
       }
                void show()
                {
                    System.out.println("\nHere Your Tokens :: -\n\n");
                    for(int z=0;z<tokenlist.size();z++)
                        {
                            System.out.println("Token :: "+tokenlist.get(z));
                        }
                }
       
       
       String Integer(String word,int lineno)
       {
           Matcher m = P.matcher(word);
            if (m.matches()== true)
            {
                classpart="Integer_Const";
                valuepart=word;
                System.out.println("Interger");
                    
                
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
                token = classpart+","+valuepart+","+lineno;
                return token;
            }
            return null;
       }
       
       
       
       String Float_Const(String word,int lineno)
       {
           
           Matcher m1= P1.matcher(word);
           if (m1.matches()== true)
            {
                classpart="Float_Const";
                valuepart=word;
                
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                
                    token = classpart+","+valuepart+","+lineno;
                return token;
            }
           return null;
       }
       
       
       
       String Characters_const(String word,int lineno)
       {
           Matcher m2= P2.matcher(word);
            if (m2.matches()== true)
            {
                classpart="Char_Const";
                valuepart=word;
                
                Tokens t = new Tokens(classpart,valuepart,lineno);
                list2.add(t);
                
                token = classpart+","+valuepart+","+lineno;
                return token;
            }
            return null;
       }

       
       
       String String_Const(String word,int lineno)
       {
           
           Matcher m3= P3.matcher(word);
           if (m3.matches()== true)
            {
                
                classpart="String_Const";
                valuepart=word;
                
                
                Tokens t = new Tokens(classpart,valuepart,lineno);
                list2.add(t);
                
                token = classpart+","+valuepart+","+lineno;
                return token;
            }
           return null;
       }
       
       
       
       String Identifiers(String word,int lineno)
       {
            
        
       Matcher m4 = P4.matcher(word);
       
       if (m4.matches()== true)
       {
           classpart="Identifier_Const";
           valuepart=word;
                
                
           Tokens t = new Tokens(classpart,valuepart,lineno);
           list2.add(t);
           
           token = classpart+","+valuepart+","+lineno;
            return token;
       }
            return null;   
       }
       
       
       
       
       String IsPunctuator(String word,int lineno)
       {
            String[] punctuator = 
            {
                ":",
                "(",
                ")",
                "{",
                "}",
                "[",
                "]",
                ",",
                ";",
                "."
            };
     
           for(int i=0;i<punctuator.length;i++)
           {
               if(word.equals(punctuator[i]))
               {
                    
                    classpart=punctuator[i];
                    valuepart=punctuator[i];
                    
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
                    token = classpart+","+valuepart+","+lineno; 
                    break;
               }
           
           }
           return token;
       }
       
       
      
       
       String IsKeywords(String word,int lineno)
       {
       
           String keyword_arr[][]= 
            {  
               {"Begin","BEGIN"}  ,
               {"Terminate","TERMINATE"},
               
               {"Looptill","LOOPTILL"},
               {"Loopwhile","LOOPWHILE"},
               
               {"Public","ACCESSMODIFIER"},
               {"Private","ACCESSMODIFIER"},
               {"Process","PROCESS"},
               
               {"Model","MODEL"},
               {"Main","MAIN"},
               {"Void","VOID"},
               
               {"If","IF"},
               {"Else","ELSE"},
               
               {"Array","ARRAY"},
               {"Return","RETURN"},
                //Printing
               //{"Console","PRINT"},
               
                //Datatypes
               {"Bool","DT"},
               {"String","DT"},
               {"Int","DT"},
               {"Char","DT"},
               {"Float","DT"},
               
               //concatoperator
               {"@","@"},
               //NEW
               {"New","NEW"},
               //Null
               {"Null","NULL"},
               
               //Break
               {"Break","BREAK"}
               
               
            }; 
           
            for(int i=0;i<23;i++)
            {
               
                for(int j=0;j<2;j++)
                {
              
                    if(word.equals(keyword_arr[i][j]) )
                    {
                        
                        if(j != keyword_arr[i].length -1)
                        {
                            classpart=keyword_arr[i][j+1];
                            valuepart=keyword_arr[i][j];
                            
                            Tokens t = new Tokens(classpart,valuepart,lineno);
                            list2.add(t);
                            
                            token = classpart+","+valuepart+","+lineno;   
                            break;
                        }
                        else
                        {
                            token=null;
                            break;
                        }
                    
                    }
            
                }
            
            
            }
            return token;
       }
       
       
       
       
       String IsOperator(String word,int lineno)
       {
         
           if(word.equals("<") || word.equals(">")|| word.equals("<<") || word.equals(">>") || word.equals("<=") || word.equals(">=") || word.equals("!=") || word.equals("==") )
           {
               classpart="ROP"; //relational operator
               valuepart=word;
               // linenumber MF
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           }
           else if(word.equals("*=") || word.equals("+=") || word.equals("-=") || word.equals("/=") || word.equals("%=") || word.equals("&=") || word.equals("|=") )
           {
               classpart="ASOP";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if( word.equals("+") || word.equals("-") )
           {
               classpart="PLUS_MINS";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if(word.equals("--") || word.equals("++"))
           {
               
               classpart="INC_DEC";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if(word.equals("/") || word.equals("%") || word.equals("*"))
           {
               
               classpart="DIV_MOD_MUL";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if(word.equals("&&"))
           {
           
               classpart="&&";
               valuepart=word;
                    
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           
           else if(word.equals("||"))
           {
           
               classpart="||";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if(word.equals("&"))
           {
           
               classpart="&";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           
           else if(word.equals("="))
           {
           
               classpart="=";
               valuepart=word;
               
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           else if(word.equals("!"))
           {
           
               classpart="!";
               valuepart=word;
                    
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
                    
               token = classpart+","+valuepart+","+lineno;
           
           }
           return token;
       }
       
       
        String Endmarker(String word,int lineno)
       {
           if(word.equals("^"))
           {
               
                    classpart="^";
                    valuepart=word;
                    
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
                    
                    token = classpart+","+valuepart+","+lineno;
           }
           return token;
       
       
       }
        
        
        String ConcatOperator(String word,int lineno)
       {
           if(word.equals("@"))
           {
               
                    classpart="@";
                    valuepart=word;
                    
                    Tokens t = new Tokens(classpart,valuepart,lineno);
                    list2.add(t);
                    
                    
                    token = classpart+","+valuepart+","+lineno;
           }
           return token;
       
       
       }
        
        
       
       void disp() //Tokens list2 
       {
           //System.out.println(list2.isEmpty());
           //System.out.println("disp Arrylist2");
           System.out.println("\n\nHere Your Tokens :: - \n\n");
           for(int i=0;i<list2.size();i++)
           {
               System.out.println(list2.get(i).cp + ", "+ list2.get(i).vp +", "+ list2.get(i).lno);
           }
       
       }
}
    