
package smartCompiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jdk.jfr.events.FileWriteEvent;

public class Lex
{
    //static int arr[];
    public static void main(String[] args) 
    {
        
        try
        {
        
        Syntax_Analyzer syn =new  Syntax_Analyzer();     
           
        lexical_Analyzer lex = new lexical_Analyzer();
        
      //arr[4]=6;
      
        File f =  new File("test.txt");
        
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        ArrayList<String> list = new ArrayList<> ();
        
       
        
        char next=' ';
        String word="",str,temp;
       	
        // File to ArrayList
        while( (temp = br.readLine() )!= null)
        {
            list.add(temp);
           // System.out.println(list);
            
        }
            // ArrayList to String
            for(int i=0;i<list.size();i++)
            {
                str = list.get(i);
                
            // String to Character
                
                for(int j=0;j<str.length();j++)
                {
                    next = str.charAt(j);
                    
                    if(( next!=' ' && next!='^' &&  next != ',' &&  next != '.' &&  next !='@' &&  next != '#' &&  next != '$' &&  next != '\"' &&  next != '{' &&  next !='}' &&  next != '\r' && next != '\n' && next != ';' && next != ':' && next != '(' && next != ')' && next != '[' && next != ']' && next != '=' && next!= '+' && next!= '-' && next!= '*' && next!= '/' && next!= '%' && next!= '>' && next!= '<' && next!= '!' && next!= '&' && next!= '|' ) )
                    {
                        
                        if(next== '\t')
                        {
                            word = "";
                        
                        }
                        else
                        {
                        word +=next;
                        }
                    }
        
                    
                    //space breaker
                    else if(next == ' ' )
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                    }
                    
                    
                    
                    //end marker breaker
                    else if(next == '^' )
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                            word +=next;
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word ="";
                    }
                    
                    //comma breaker
                    
                    else if(next == ',' )
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                            word +=next;
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word ="";
                        
                    }
                     
                    
                //method callig
                else  if(next == '@')
                {   
                    if(word !="")
                    {
                    System.out.println("word :: "+word);
                    lex.TokenGenerator(word, i);
                    word="";
                    
                    // @
                        word+=next;
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                    }
                    else
                    {
                        // @
                        word+=next;
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                    
                    }
                    /*
                        word="";
                        j++;
                        int l=j;*/
                    /*
                        for(l=j;l<str.length();l++)
                        {
                        next=str.charAt(l);
                        if( (next >='a' && next <='z') || (next >='A' && next <='Z') )
                        {
                            word +=next;
                        }
                        else 
                        {
                            j=l;
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word ="";
                            l--;
                            j=l;
                            break;
                        }
                
                        }*/
                   // j=l;
                    
                }
                
                
                
                
                // . breaker
                    else if(next == '.' )
                    {
                        if( (word !="" ) && ( (word.charAt(0) >= 'a' && word.charAt(0) <='z') || (word.charAt(0) >= 'A' && word.charAt(0) <='Z') ) ||  ( word.startsWith("_") )) 
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                            
                            if( ( (str.charAt(j+1) >= 'a' && str.charAt(j+1) <='z') || (str.charAt(j+1) >= 'A' && str.charAt(j+1) <='Z') ) && (j!=str.length()-1) )
                            {
                                word+=next;
                                System.out.println("word :: "+word);
                                lex.TokenGenerator(word, i);
                                word="";
                                j++;
                            }
                        }
                        
                        
                        int k=j;
                        if(k!=str.length()-1)
                        {
                         word +=next;
                            k++;
                         for(;k<str.length();k++)
                         {
                            next=str.charAt(k);
                            
                            if( next!='^' &&  next != ',' &&  next != '.' &&  next !='@' &&  next != '#' &&  next != '$' &&  next != '\"' &&  next != '{' &&  next !='}' &&  next != '\r' && next != '\n' && next != ';' && next != ':' && next != '(' && next != ')' && next != '[' && next != ']' && next != '=' && next!= '+' && next!= '-' && next!= '*' && next!= '/' && next!= '%' && next!= '>' && next!= '<' && next!= '!' && next!= '&' && next!= '|' )
                            {
                                word +=next;
                            }
                            
                            else if(next =='.')
                            {
                                System.out.println("word :: "+word);
                                lex.TokenGenerator(word, i);
                                word="";
                                
                                if(k!=str.length()-1)
                                if( ( (str.charAt(k+1) >= 'a' && str.charAt(k+1) <='z') || (str.charAt(k+1) >= 'A' && str.charAt(k+1) <='Z') ) && (k!=str.length()-1) )
                                {
                                word+=next;
                                System.out.println("word :: "+word);
                                lex.TokenGenerator(word, i);
                                word="";
                                
                                }
                                else word +=next;
                                
                            }
                            
                            
                            else
                            {
                                System.out.println("word :: "+word);
                                lex.TokenGenerator(word, i);
                                word="";
                                word +=next;
                                break;
                            }                      
                        }
                        }
                        else
                        {
                            word +=next;
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        
                        if(word !="")
                        {
                               
                                System.out.println("word :: "+word);
                                lex.TokenGenerator(word, i);
                                word="";
                        }
                        j=k;
                    }
                                
                    //comment breaker Single #
                    
                    else if(next =='#' )
                    {
                       if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                       
                        word +=next;
                        if(j != str.length()-1)
                        if(str.charAt(j+1) == '#')
                        {
                            word="";
                            for(int z=j;z<str.length();z++)
                            {
                                j=z;
                                word="";
                            }
                        }
                        else
                        {
                        
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                    }
                    
                    //Mulitu line Comment breaker $$
                    
                     else if(next == '$' )
                    { 
                        //System.out.println("$ encounter !!");
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        int p=i;
                        int q=j;
                        
                        
                        
                        if(q != str.length() -1)
                        {
                            word +=next;
                            q++;
                            for(;p<list.size();)
                            {
                              
                                for(;q<str.length();q++)
                                {
                                    next=str.charAt(q);
                                   
                                    if(next == '$')
                                    {
                                        word +=next;
                                        break;
                                    }
                                    else
                                    {
                                    
                                        word +=next;
                                    }
                            
                               }//inner loop
                                
                               
                                if(word.startsWith("$") && word.endsWith("$"))
                                {
                                     System.out.println("comment :: "+word);
                                     word="";
                                     i=p;
                                     j=q;
                                     break;
                                }
                                
                                if(p >list.size())
                                {
                                    
                                 break;   
                                }
                                else
                                {
                                    p++;
                                    if(p >list.size())
                                    {
                                        break;
                                    }
                                    if(p<list.size())
                                    {
                                        str=list.get(p);
                                        q=0;
                                    }
                                }
                                    
                                
                            }//upper loop
                            i=p;
                            j=q;
                             
                             
                        }
                        else
                        {
                            
                            word +=next;
                            p++;
                            if(p<list.size())
                            {
                                for(;p<list.size();p++)
                                {
                                str=list.get(p);
                                for(q=0;q<str.length();q++)
                                {
                                    next=str.charAt(q);
                                    if(next !='$')
                                    {
                                        word +=next;
                                    }
                                    else
                                    {
                                        word +=next;
                                        i=p;
                                        j=q;
                                        break;
                                    }
                                    
                                }
                                
                                if(word.startsWith("$") && word.endsWith("$"))
                                {
                                     System.out.println("comment :: "+word);
                                     word="";
                                     i=p;
                                     j=q;
                                     break;
                                }
                                
                                
                                
                                }
                                i=p;
                                j=q;
                             
                                
                            }
                            
                            
                            
                        }
                        
                        
                    }
                    
                    
                    //String breaker
                    else if(next == '\"')
                    {
                        if(word !="")
                        {   
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        int k;
                        
                       
                        k=j;
                        
                        if(k!=str.length()-1)
                        {
                            
                         word +=next;
                         k++;
                            for(;k<str.length();k++)
                            {
                             next=str.charAt(k);
                            if(next == '\\')
                            {   word +='\\';
                                if(k!=str.length()-1)
                                if(str.charAt(k+1)=='\"' )
                                {
                                    word +='\"';
                                    k++;
                                }
                                
                                
                            }
                            
                            else if(next !='\"')
                            {
                                word +=next;
                            
                            }
                            else
                            {
                                word +=next;
                                lex.TokenGenerator(word, i);
                                j=k;
                                System.out.println("word :: "+word);
                                word ="";
                                break;
                            }
                        
                        }  j=k;
                        }
                        else
                        {
                         word +=next;
                            System.out.println("word :: "+word);
                         lex.TokenGenerator(word, i);
                         word="";
                         j=k;
                        }
                        if(word.endsWith("\"") && word.charAt(word.length()-2)=='\\'  )
                        {  
                            lex.tokenlist.add("invalid lexeme"+","+word+","+i);
                            word="";
                        }
                    }
                    
                    


                    //Char breaker
                    else if(next == '\'')
                    {
                       word +=next;
                       j++;
                        for(int k=j;k<str.length();k++)
                        {
                            next=str.charAt(k);
                            if(next !='\'')
                            {
                                word +=next;
                            }
                            else
                            {
                                word +=next;
                                lex.TokenGenerator(word, i);
                                j=k;
                                System.out.println("word :: "+word);
                                word ="";
                                break;
                            }
                        }
                    }
                    
                    
                           
                    // Punctuator breaker
                    else if(next == '{')
                    {
                        //System.out.println("small bracket");
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        System.out.println("word :: "+next);
                        lex.TokenGenerator("{", i);
                        word="";
                        
                    }
                    
                    else if(next == '}')
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        System.out.println("word :: "+next);
                        lex.TokenGenerator("}", i);
                        
                        word="";
                    
                    }
                    
                    else if(next == '(')
                    {
                        if(word != "")
                        {
                           
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        System.out.println("word :: "+next);
                        lex.TokenGenerator("(", i);
                        word="";
                        
                    }
                    
                    else if(next == ')')
                    {
                        if(word != "")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        System.out.println("word :: "+next);
                        lex.TokenGenerator(")", i);
                        word="";
                        
                    }
                    
                    else if(next == ':')
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word ="";
                        }
                          
                            System.out.println("word :: "+next);
                            lex.TokenGenerator(":", i);
                            word="";
                        
                    }
                    
                    else if(next == ';')
                    {
                        if(word !="")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                            word +=next;
                            lex.TokenGenerator(word, i);
                            System.out.println("word :: "+word);
                            word="";
                        
                    }
                    
                    
                    else if(next == '[')
                    {
                        if(word !="")
                        {
                        
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                        
                            System.out.println("word :: "+next);
                            lex.TokenGenerator("[", i);
                            word="";
                        
                    }
                    
                    else if(next == ']')
                    {
                        if(word !="")
                        {
                        
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            word="";
                        }
                        
                            System.out.println("word :: "+next);
                            lex.TokenGenerator("]", i);
                            word="";
                        
                    }

                    
                    // == Breaker
                    else if(next == '=')
                    {
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    // ++ breaker
                    else if(next == '+')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '+' )
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    // -- breaker
                    else if(next == '-')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                            }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '-' )
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    //*breaker
                    else if(next == '*')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' )
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    // /= breaker
                    
                    else if(next == '/')
                    {
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' )
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    // %= breaker
                    else if(next == '%')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    // >> breaker
                    
                    else if(next == '>')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '>')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    
                    //<< breaker
                    else if(next == '<')
                    {
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '<')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    // && breaker
                    else if(next == '&')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '&')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        
                    }
                    
                    
                    // !- breaker
                    else if(next == '!')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    
                    // || breaker
                    
                    else if(next == '|')
                    {
                        
                        if(word !="")
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                        
                        word +=next;
                        
                        if(j !=str.length()-1)
                        if(str.charAt(j+1) == '=' || str.charAt(j+1) == '|')
                        {
                            word +=str.charAt(j+1);
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            j++;
                            word="";
                        
                        }
                        else
                        {
                        System.out.println("word :: "+word);
                        lex.TokenGenerator(word, i);
                        word="";
                        }
                    }
                    
                    
                    
                    

                }//this loop ends , mean line ends , so after this loop i will have a new word/token
                        if(word != "")
                        {
                            System.out.println("word :: "+word);
                            lex.TokenGenerator(word, i);
                            
                            word="";
                        }    
        
        
        
            }
            
            br.close();
            fr.close();
            
            //lex.show(); // Token show method
            
            lex.disp();//Tokens list2
           // System.out.println(syn.S());
            syn.validate();
            
            File f1 = new File("TokenFile.txt");
            FileWriter fw  = new FileWriter(f1);
            BufferedWriter bw  = new BufferedWriter(fw);
            

            // Token write on TokenFile
            
            for(int i=0;i<lex.tokenlist.size();i++)
            {
                bw.write("[ "+lex.tokenlist.get(i)+" ]");
                bw.newLine();
            
            }
            bw.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        
        }
      
        
    }

    
}


