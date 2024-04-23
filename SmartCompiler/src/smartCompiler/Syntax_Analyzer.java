
package smartCompiler;

import java.util.ArrayList;
import static smartCompiler.lexical_Analyzer.list2;

public class Syntax_Analyzer 
{
    lexical_Analyzer lex2 = new lexical_Analyzer();
  
    static int i=0;
    //start cfg
  
    boolean validate()
    {
        if( S() )
        {
        
            if(list2.get(i).cp.equals("^") )
            {
 
                System.out.println("\n\ntrue syntax");
                return true;
            }
        }
        
        
        System.out.println("\n\nError Syntax on line "+list2.get(i).lno);
        return false;
    }
    
    
    
    
    boolean S()
    {
    
        if(list2.get(i).cp.equals("BEGIN"))
        {
            i++;
            
            if( Main() )
            {
            
                if(list2.get(i).cp.equals("TERMINATE"))
                {
                    
                    i++;
                    
                    return true;
                }
                
                
                
            }
            
        
        
        }
        
        
        return false;
    }
    
    
    
    //Main cfg
    
    boolean Main()
    {
    
        if(list2.get(i).cp.equals("MAIN"))
        {
        
        
            i++;
            if(list2.get(i).cp.equals("("))
            {
            
                i++;
                if(list2.get(i).cp.equals(")"))
                {
                
                    i++;
                    if(list2.get(i).cp.equals("{"))
                    {
                       
                        i++;
                        
                        if( MST() )
                        {
                        
                            if(list2.get(i).cp.equals("}"))
                            {
                            
                                i++;
                            
                                if( MST() )
                                {
                                    return true;
                                }
                                
                            }
                        
                        }
                    
                    }
                }
            }
        }
        
   
    return false;
    }//Main cfg end
    
    
    
    
    // MST cfg start
    boolean MST()
    {
    //only 1 rule
        if( SST() )
        {
        
            if( MST() )
            {
            
                return true;
            }
        }
        
        //sel set
        else if(list2.get(i).cp.equals("}") || list2.get(i).cp.equals("TERMINATE") || list2.get(i).cp.equals("RETURN") )
        {
       
        
            return true;
        }
        
    
        return false;
    } //MST cfg end
    
    
    //SST cfg start
    boolean SST()
    {
        //rule 1
    
        if(list2.get(i).cp.equals("DT"))
        {
        
            i++;
            
            if( FACT5_INIT () )
            {
            
                return true;
            
            }
        
        
        }
        
        //rule 2
        else if( IF_ELSE() )
        {
        
            return true;
        }
        
        
        //rule 3
        else if( LOOP_TILL() )
        {
        
            return true;
        }
        
        
        //rule 4
        else if( LOOP_WHILE() )
        {
            
            return true;
        }
        
 /*       
        //rule 5
        else if( AE() )
        {
        
            if( OE_dash() )
            {
            
                return true;
            }
        }
   */     
      //rule 6
        else if(list2.get(i).cp.equals("Identifier_Const"))
        {
            i++;
            if( FACT1_SST() )
            {
            
                return true;
            }
        }
        
        //rule 7
        else if( AM() )
        {
        
            if( FACT2_SST() )
            {
            
                return true;
            }
        }
        
        
        //rule 8 New
        else if(list2.get(i).cp.equals("INC_DEC"))
        {
            i++;
            if(list2.get(i).cp.equals("Identifier_Const"))
            {
                i++;
                if(Z())
                {
                if(list2.get(i).cp.equals(":"))
                {
                    i++;
                    return true;
                
                }
                }
            
            }
        
        }
        
        //new rule 9
        
        
        
    return  false;
    }//SST CFG End
    
    
    
    // Z()
    boolean Z()
    {
        
        //rule 1
        if(list2.get(i).cp.equals("["))
        {
            i++;
            if(EXP())
            {
                if(list2.get(i).cp.equals("]"))
                {
                    i++;
                    return true;
                }
            
            }
        
        }
   
        
        //sel set
        else if(list2.get(i).cp.equals("INC_DEC") || list2.get(i).cp.equals(":") )
        {
            return true;
        
        }
    
    return false;
    }
    
    //FACT1_Z()
    boolean FACT1_Z()
    {
        //onlu rule 1
        
        if(list2.get(i).cp.equals("["))
        {
            i++;
            if(EXP())
            {
                if(list2.get(i).cp.equals("]"))
                {
                    i++;
                    return true;
                
                }
            
            }
        
        }
        
        //sel set
        else if(list2.get(i).cp.equals("INC_DEC") || list2.get(i).cp.equals(":") )
        {
            return true;
        
        }
    
    return false;
    }
    
          
    //FACT1_SST cfg start
    boolean FACT1_SST()
    {
        //rule 1
        if(list2.get(i).cp.equals("@"))
        {
        
            i++;
                
                if( FACT3_SST() )
                {
                
                    return true;
                }
            }
        
        
        //rule 2
        else if(list2.get(i).cp.equals("Identifier_Const"))
        {
            i++;
        
            if( FACT7_INIT() )
            {
                return true;
            }
        }
        /*
        //rule 3
        
        else if( ASSGN_FACT1() )
        {
        
            return true;
        }
    */
        //New rule 3
        
        else if(ASSGN_Y())
        {
            if(FACT4_SST())
            {
                return true;
            
            }
        
        
        
        }
        
        
        
        
    /*    

            //rule 4
        
        else if(list2.get(i).cp.equals("("))
        {
        
            i++;
            
            if( PARA() )
            {
            
                if(list2.get(i).equals(")"))
                {
                    i++;
                    if( T_dash() )
                    {
                    
                        if( E_dash() )
                        {
                        
                            if( RE_dash() )
                            {
              
                                if ( AE_dash() )
                                {
                                
                                    if( OE_dash() )
                                    {
                                        return true;
                                    
                                    }
                                
                                }
                                
                            
                            }
                        
                        }
                        
                    
                    
                    }
                
                
                }
            
            
            }
        
        }
        
        
        //rule 5
       else if( EXP_X() )
        {
            if( T_dash() )
            {
                if(E_dash())
                {
                
                    if( RE_dash() )
                    {
                        if( AE_dash() )
                        {
                            if( OE_dash() )
                            {
                                
                            
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        //rule 6
        else if(list2.get(i).equals("["))
        {
            i++;
            if( EXP() )
            {
            
                if(list2.get(i).equals("]"))
                {
                    i++;
                    
                    if( FACT2_EXP() )
                    {
                        if( T_dash() )
                        {
                            if( E_dash() )
                            {
                                if( RE_dash() )
                                {
                                    if( AE_dash() )
                                    {
                                        if( OE_dash() ) 
                                        {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
     
        //rule 7
        else if ( T_dash() )
        {
            if( E_dash() )
            {
                if( RE_dash() )
                {
                    if(AE_dash())
                    {
                        if( OE_dash() )
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        //selection set FACT1_SST
        else if(list2.get(i).cp.equals("DT") || list2.get(i).cp.equals("IF") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ACCESSMODIFIER")  )
        {
            return true;
        }
        
      */  
        
        
        

        //rule 8
            //baad me add hua hai
        
        else if(list2.get(i).cp.equals("("))
        {
        
            i++;
            
            if( PARA() )
            {
            
                if(list2.get(i).cp.equals(")"))
                {
                    i++;
                    if(list2.get(i).cp.equals(":"))
                    {
                        i++;
                        return true;
                    
                    }
                    
                }
                
            }
                
        }
        
        
        
        //rule 9 New
     if(Z())
     {
         if(list2.get(i).cp.equals("INC_DEC"))
        {
            i++;
            if(list2.get(i).cp.equals(":"))
            {
                i++;
               return true;
            }
        
        }
    }
        
        //rule 10 New
        
        else if(list2.get(i).cp.equals("["))
        {
            i++;
            if(EXP())
            {
                if(list2.get(i).cp.equals("]"))
                {
                    i++;
                    if(ASSGN_FACT3())
                    {
                    
                        return true;
                    }
                
                }
            
            }
        
        }
        
        //rule 11
        else if(EXP() )
        {
            if(ASSGN_LIST())
            {
                return true;
            
            }
        
        
        }
        
    return false;
    }//FACT1_SST cfg end
    
    
    //FACT_2 SST cfg start
    boolean FACT2_SST()
    {
    
        //rule 1
        if( FN_CREAT_FACT1() )
        {
            return true;
        }
        
        //rule 2
        else if(list2.get(i).cp.equals("MODEL"))
        {
         
            i++;
            if(list2.get(i).cp.equals("Identifier_Const"))
            {
                i++;
                if(list2.get(i).cp.equals("{"))
                {
                    i++;
                    if( CLASS_X() )
                    {
                        if(list2.get(i).cp.equals("}"))
                        {
                        
                            i++;
                            return true;
                        }
                    }
                }
            }
        }
    
    
    return false;
    }//FACT_2 SST cfg end
    
    
    
    //FACT_3 SST cfg start
    
    boolean FACT3_SST()
    {
        /*
        
        //only 1 rule
        if(list2.get(i).cp.equals("("))
        {
            i++;
            if( PARA() )
            {
                if(list2.get(i).cp.equals(")"))
                {
                    i++;
                    if(list2.get(i).cp.equals(":"))
                    {
                        i++;
                        return true;
                    }
                }
            }
        }
        
        */
        /*
        else if( FACT3_EXP() )
        {
            return true;
        }
        */
        
        // rule 2 new 
         if(list2.get(i).cp.equals("INC_DEC") )
        {
            i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(Z())
               {
                   if(list2.get(i).cp.equals(":"))
                   {
                       i++;
                       return true;
                   
                   }
               
               }
           
           }
        
        }
        
        
        /*
        //rule 3 New
        else if(Z())
        {
            if(list2.get(i).cp.equals("INC_DEC"))
            {
                i++;
                if(list2.get(i).cp.equals(":"))
                {
                    i++;
                    return true;
                
                }
            
            }
        
        
        }*/
         
         
         else if(list2.get(i).cp.equals("Identifier_Const"))
         {
             i++;
             if(FACT5_SST() )
             {
                 
                 return true;
             
             }
         
         
         }
    return false;
    }//FACT_3 SST End
    
    
    
    //FACT4_SST()
    boolean FACT4_SST()
    {
        //rule 1
        if(ASSGN_FACT6())
        {
            return true;
        }
    //rule 2
        else if(ASSGN_FACT2())
        {
            return true;
        
        }
    
    
    return false;
    }
    
    //FACT5_SST()
    boolean FACT5_SST()
    {
        if(list2.get(i).cp.equals("("))
        {
            i++;
            if( PARA() )
            {
                if(list2.get(i).cp.equals(")"))
                {
                    i++;
                    if(list2.get(i).cp.equals(":"))
                    {
                        i++;
                        return true;
                    
                    }
                }
            }
        }
        
    //rule2 2 NEW
        else if(Z())
        {
            if(list2.get(i).cp.equals("INC_DEC"))
            {
                i++;
                if(list2.get(i).cp.equals(":"))
                {
                    i++;
                    return true;
                
                }
            
            }
        
        
        }
    return false;
    }
    
    //Expression cfg starts
    boolean EXP()
    {
        //rule 1
        if( AE() )
        {
            if( OE_dash() )
            {
                return true;
            }
        }
        
        
        //rule 2
        else if(list2.get(i).cp.equals("Identifier_Const"))
        {
        
            i++;
            
            if( FACT1_EXP() )
            {
                if( T_dash() )
                {
                    if( E_dash() )
                    {
                        if( RE_dash() )
                        {
                            if( AE_dash() )
                            {
                                if( OE_dash() )
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //rule 3
        
       else if(list2.get(i).cp.equals("("))
        {
            i++;
            if( EXP() )
            {
                if(list2.get(i).cp.equals(")"))
                {
                    i++;
                
                    if( T_dash() )
                    {
                        if( E_dash() )
                        {
                            if( RE_dash() )
                            {
                                if( AE_dash() )
                                {
                                    if( OE_dash() )
                                    {
                                    
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
    return false;
    }
    
    
    boolean OE_dash()
    {
        //rule 1
        if(list2.get(i).cp.equals("||"))
        {
            i++;
            if( FACTOE_dash() )
            {
                return true;
            }
        }
        
        
        //sel set
        else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
        {
            return true;
        }
    
    return false;
    }
    
    
    
    
    //AE cfg
    boolean AE()
    {
        //only 1 rule
        if( RE() )
        {
            if( AE_dash() )
            {
                return true;
            }
        }
    
    return false;
    }
    
    
    boolean AE_dash()
    {
        //only 1 rule
        if(list2.get(i).cp.equals("&&"))
        {
            i++;
            if( FACTAE_dash() )
            {
                return true;
            }
        
        }
        //sel set
        else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
        {
            return true;
        }
        
    
    return false;
    }
    
    //RE cfg
    boolean RE()
    {
        //1 rule only
        if(E())
        {
            if( RE_dash() )
            {
                return true;
            
            
            }
        }
    return false;
    }
    
    
   boolean RE_dash()
   {
       if(list2.get(i).cp.equals("ROP"))
       {
           i++;
           if( FACTRE_dash() )
           {
               return true;
           }
       }
       
       //sel set
       else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
       {
           return true;
       }
       
       
   return false;
   }
    
   
   //T cfg
   
   boolean T()
   {
       // only 1 rule 
       if( F() )
       {
           if( T_dash() )
           {
               return true;
           }
       }
       
   return false;
   }
   
   //T_dash() cfg
   
   boolean T_dash()
   {
       //only 1 rule
       if(list2.get(i).cp.equals("DIV_MOD_MUL") )
       {
           i++;
           if( FACTT_dash() )
           {
           
               return true;
           }
       }
       //sel set update
        else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}")      )
       {
           return true;
       }
   
   return false;
   }

    //E cfg
   
   boolean E()
   {
       if( T () )
       {
           if( E_dash() )
           {
               return true;
           
           
           }
       }
   return false;
   }
   
   //E_dash() cfg
   
   boolean E_dash()
   {
       if(list2.get(i).cp.equals("PLUS_MINS"))
       {
           i++;
           if( FACTE_dash() )
           {
               return true;
           
           }
       
       
       }
       
       //sel set
       else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
       {
           return true;
       }
   
   
   return false;
   }
   
  //FACTT_dash() cfg
   
   boolean FACTT_dash()
   {
       //rule 1
       if( F() )
       {
           if( T_dash() )
           {
               return true;
           }
       }
       //rule 2
       
       else if(list2.get(i).cp.equals("("))
       {
           i++;
           if( EXP() )
           {
               if(list2.get(i).cp.equals(")"))
               {
               
                   i++;
                   if( T_dash() )
                   {
                       return true;
                   }
               }
           }
        }
       
       //rule 3
       
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT1_EXP() )
           {
               if( T_dash() )
               {
               
                   return true;
                }
           }
        }
   
   return false;
   }
   
   
   //FACTE_dash() cg start

   boolean FACTE_dash()
   {
       //rule 1
       if( T() )
       {
           if( E_dash() )
           {
               return true;
           }
       }
       
       
       //rule 2
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT1_EXP() )
           {
               if( T_dash() )
               {
                   if( E_dash() )
                   {
                   
                       return true;
                   
                   }
                }
            }
        }
       
   return false;
   }
   
   
   //FACTRE_dash() cfg start
   
   boolean FACTRE_dash()
   {
       //rule 1
       if( E() )
       {
           if( RE_dash() )
           {
               return true;
           
           }
       }
       
       //rule 2
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
               if( FACT1_EXP() )
            
               {
               
                   if( T_dash() )
               
                   {
                   
                       if( E_dash() )
                   
                       {
                  
                           if( RE_dash() )
                           {
                               return true;
                           
                           
                           }
                       }
                
                   }
            
               }
       }
       
   return false;
   }
   
   //FACTAE_dash() cfg start
   boolean FACTAE_dash()
   {
       
       //rule 1
       if( RE() )
       {
           if( AE_dash() )
           {
               return true;
           }
        }
       //rule 2
       
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
       
           i++;
           if( FACT1_EXP() )
            
               {
               
                   if( T_dash() )
               
                   {
                   
                       if( E_dash() )
                   
                       {
                  
                           if( RE_dash() )
                           {
                               if( AE_dash() )
                               {
                                   return true;
                               }
                           }
                       }
                }
            }
        }
   
   
   return false;
   }
   
   //FACTOE_dash() cfg
   
   boolean FACTOE_dash()
   {
       //rule 1
       if( AE() )
       {
           if( OE_dash() )
           {
               return true;
            }
        }
       
       //rule 2
       
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT1_EXP() )
            
               {
               
                   if( T_dash() )
               
                   {
                   
                       if( E_dash() )
                   
                       {
                  
                           if( RE_dash() )
                           {
                               if( AE_dash() )
                               {
                                   if(OE_dash() )
                                   {
                                       return true;
                                   }
                                }
                            }
                        }
                
                   }
            
               }
        }
       
       //rule 3
      else if(list2.get(i).cp.equals("("))
        {
            i++;
            if( EXP() )
            {
                if(list2.get(i).cp.equals(")"))
                {
                    i++;
                
                    if( T_dash() )
                    {
                        if( E_dash() )
                        {
                            if( RE_dash() )
                            {
                                if( AE_dash() )
                                {
                                    if( OE_dash() )
                                    {
                                    
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
   
   return false;
   }
   
   
   //FACT1_EXP() cfg start
   
   boolean FACT1_EXP()
   {
       //rule 1
       if(list2.get(i).cp.equals("("))
       {
           i++;
           if( PARA() )
           {
               if(list2.get(i).cp.equals(")"))
               {
                   i++;
                   return true;
               }
           }
        }
   
       
       //rule 2
       
       else if( EXP_X() )
       {
           return true;
       }
       
       //rule 3
       
       else if(list2.get(i).cp.equals("["))
       {
           i++;
           
           if( EXP() )
           {
               if(list2.get(i).cp.equals("]"))
               {
                   i++;
                   
                   if( FACT2_EXP() )
                   {
                       return true;
                   
                   }
               }
           }
       }
       
       //rule 4
       
       else if(list2.get(i).cp.equals("@"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               
               if( FACT3_EXP() )
               {
                   return true;
               }
            }
        }
       
       //sel set
       else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
       {
           return true;
       }
       
   return false;
   }
   
   // FACT2_EXP() cfg start
   
   boolean FACT2_EXP()
   {
       //rule 1
       if(list2.get(i).cp.equals("["))
       {
           i++;
           
           if( EXP() )
           {
               if(list2.get(i).cp.equals("]"))
               {
                   i++;
                   return true;
               
               }
           }
       }
       
       //rule 2
       else if(list2.get(i).cp.equals("@"))
       {
           i++;
       
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               return true;
           }
        }
   
       //sel set
       else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}") )
       {
           return true;
       }
   
   return false;
   }
   
   //FACT3_EXP() cfg start
   
   boolean FACT3_EXP()
   {
       if(list2.get(i).cp.equals("("))
       {
           i++;
           if( PARA() )
           {
               if(list2.get(i).cp.equals(")"))
               {
                   i++;
                   return true;
               }
           }
        }
       
       //sel set
       else if(list2.get(i).cp.equals("DIV_MOD_MUL") || list2.get(i).cp.equals("PLUS_MINS") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-") || list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("ROP") || list2.get(i).cp.equals("&&") || list2.get(i).cp.equals("||") || list2.get(i).cp.equals(")") || list2.get(i).cp.equals(":") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("If") || list2.get(i).cp.equals("]") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals("}"))
       {
           return true;
       }
   
   
   return false;
   }
   
   
   //EXP_X() cfg start
   
   boolean EXP_X()
   {
       //rule 1
       
       if(list2.get(i).cp.equals("INC_DEC") )
       {
           i++;
           return true;
       }
   
       return false;
   }
   
   //EXP_F() cfg start
   
   boolean F()
   {
       //rule 1
       if(list2.get(i).cp.equals("INC_DEC") )
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               return true;
           }
           
       }
       //rule 2
       
       else if(list2.get(i).cp.equals("!"))
       {
           i++;
           if( F() )
           {
               return true;
           }
        }
       
       //rule 3
       
       else if(list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const"))
       {
           i++;
           return true;
           
        }
       
   
   
   return false;
   }
   
   //PARA cfg start
   boolean PARA()
   {
       if( EXP() )
       {
           if( LIST_PARA() )
           {
               return true;
           }
       }
   
       //sel set
       else if(list2.get(i).cp.equals(")") )
       {
           return true;
       }
   
   return false;
   }
   
   //PARA_LIST() cfg start
   
   boolean LIST_PARA()
   {
       if(list2.get(i).cp.equals(","))
       {
           i++;
           if( EXP() )
           {
               if( LIST_PARA () )
               {
                   return true;
               }
           }
       }
       
       //sel set
       else if(list2.get(i).cp.equals(")"))
       {
           return true;
       }
    
   return false;
   }
   
   
   //IF_ELSE cfg start
   
   boolean IF_ELSE()
   {
      //rule 1
       if(list2.get(i).cp.equals("IF"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                   
                       {
                       
                           i++;
                       
                           if( IF_ELSE_BODY() )
                       
                           {
                           
                               if(list2.get(i).cp.equals("}"))
                           
                               {
                               
                                   i++;
                               
                                   if( OELSE() )
                               
                                   {
                                   
                                   return true;
                               
                                   }
                           
                               }
                       
                           }
                    
                       }
                
                   }
           
               }
           
           }
       
       }
   return false;
   }
   
   //IF_ELSE_BODY() cfg start
   
   boolean IF_ELSE_BODY()
   {
       //rule 1
       
       if( IF_ELSE () )
       {
           if( IF_ELSE_BODY() )
           {
               return true;
           }
       
       }
       
      //rule 2
      
       else if(list2.get(i).cp.equals("LOOPTILL"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               if( EXP () )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if(LOOP_TILL_BODY())
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if(IF_ELSE_BODY())
                                   {
                                       return true;
                                   }
                                }
                           }
                       }
                    }
               
               }
           }
       }
   
   
       
       //rule 3
       else if(list2.get(i).cp.equals("LOOPWHILE"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP () )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if(LOOP_WHILE_BODY())
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if(IF_ELSE_BODY())
                                   {
                                       return true;
                                   }
                                }
                           }
                       }
                    }
               
               }
           }
       }
       
       //rule 4
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           
           if( FACT1_IF_ELSE() )
           {
               return true;
           }
       }
       
       
       
       //rule 5
       else if(list2.get(i).cp.equals("RETURN"))
       {
           i++;
           
           if( FACT2_IF_ELSE () ) 
           {
               if( list2.get(i).cp.equals(":") )
               {
                   i++;
                   return true;
               }
            }
       }
       
       
       //rule 6
       else if(list2.get(i).cp.equals("BREAK"))
       {
           i++;
           if(list2.get(i).cp.equals(":"))
           {
               i++;
               if( IF_ELSE_BODY() )
                {
               
                    return true;
                }
            }
       }
       /*
       //rule 7
       else if( AE() )
       {
           if( EXP() )
           {
               if(IF_ELSE_BODY() )
               {
                   return true;
               
               }
           
           
           }
       
       
       
       
       }
*/       
       
       
       
       //rule 6
       
       else if(list2.get(i).cp.equals("DT"))
       {
           i++;
           if(FACT5_INIT())
           {
               if(IF_ELSE_BODY())
               {
                   return true;
               
               
               }
               
           
           
           }
       
       
       
       
       }
       
       //rule 7 New
       else if(list2.get(i).cp.equals("INC_DEC"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
           if(list2.get(i).cp.equals(":"))
           {
               i++;
               if(IF_ELSE_BODY())
               {
                   return true;
               
               }
           
           }
           }
       }
      //sel set
       else if(list2.get(i).cp.equals("}"))
       {
           return true;
       }
      
   return false;
   }
   
   
   //FACT1_IF_ELSE () cfg start
   
   boolean FACT1_IF_ELSE ()
   {
       //rule 1
       if(list2.get(i).cp.equals("@"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).cp.equals("(") )
               {
                   i++;
                   if( PARA() )
                   {
                       if( list2.get(i).cp.equals(")") )
                       {
                           i++;
                           if( list2.get(i).cp.equals(":") )
                           {
                               i++;
                               if(IF_ELSE_BODY())
                               {
                                   return true;
                               }
                           }
                       }
                    }
                }
            }
       }
       
       //rule 2
       else if( list2.get(i).cp.equals("(") )
       {
           i++;
           if( PARA() )
           {
               if( list2.get(i).cp.equals(")") )
               {
                   i++;
                   if(list2.get(i).cp.equals(":"))
                   {
                       i++;
                   if( IF_ELSE_BODY() )
                   {
                       return true;
                    }
                }
           }}
        }
   

       //rule 3
       
       else if(ASSGN_FACT1())
       {
           if(IF_ELSE_BODY())
           {
               return true;
           
           } 
       
       }
       
       //rule 4 New
       else if(list2.get(i).cp.equals("INC_DEC"))
       {
           i++;
           if(list2.get(i).cp.equals(":"))
           {
               i++;
               if(IF_ELSE_BODY())
               {
                   return true;
               
               }
           
           }
       
       }
       
       //rule 5 New
       
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if(FACT7_INIT())
           {
               if(IF_ELSE_BODY())
               {
                   return true;
               
               }
           
           }
       
       
       }
       
       
       //rule 6 NEW
       
       else if(ASSGN_Y())
       {
           if(FACT4_SST())
           {
               if(IF_ELSE_BODY())
               {
                   return true;
               
               }
           
           }
       
       }
       
       
   return false;
   }
   
   
   //FACT2_IF_ELSE
   
   boolean FACT2_IF_ELSE()
   {
       //only 1
       if( EXP() )
       {
           return true;
       }
       
       //rule 3 New
       
       else if(list2.get(i).cp.equals("NEW"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               
               if(list2.get(i).cp.equals("("))
               {
                   i++;
                   if(FN_CALL_PARA())
                   {
                       if(list2.get(i).cp.equals(")"))
                       {
                           i++;
                          return true;
                       }
                   
                   }
                   
               
               }
           
           }
       
       
       }
       
   
       //sel set
       
       else if(list2.get(i).cp.equals(":"))
       {
           return true;
       }
    return false;
   }
   
   
   boolean FACT3_IF_ELSE()
   {
      // rule 1
       if(list2.get(i).cp.equals("{"))
       {
           i++;
          if(IF_ELSE_BODY())
          {
              if(list2.get(i).cp.equals("}"))
              {
                  i++;
                  return true;
              }
          
          }
       }
       
       //rule 2
       else if(IF_ELSE())
       {
           return true;
       }
       
   return false;
   }
   
   
   //OELSE 
   
   boolean OELSE()
   {
       //only rule 1
       if(list2.get(i).cp.equals("ELSE"))
       {
           i++;/*
         if(X_IF_ELSE())  
         {
           if(list2.get(i).cp.equals("{"))
           {
               i++;
               if(IF_ELSE_BODY())
               {
                   if( list2.get(i).cp.equals("}") )
                   {
                       i++;
                       return true;
                   }
                }
           }
       }*/
           
           if(FACT3_IF_ELSE())
           {
               return true;
           }
       }
       
       //sel set
        else if(list2.get(i).cp.equals("}") || list2.get(i).cp.equals("TERMINATE") || list2.get(i).cp.equals("RETURN") )
        {
            return true;
        }
   
   
   return false;
   }
   
   //X_IF_ELSE()
   boolean X_IF_ELSE()
   {
       //only 1 rule
       
       if(IF_ELSE())
       {
           return true;
       }
       
       //sel set
       else if(list2.get(i).cp.equals("{"))
       {
           return true;
       }
   
   
   
   return false;
   }
   
   //LOOPTILL 
   
   boolean LOOP_TILL()
   {
       //1 only
       if(list2.get(i).cp.equals("LOOPTILL"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_TILL_BODY() )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   return true;
                               }
                           }
                       }
                   }
               }
           }
        }
      
   return false;
   }
   
   //LOOP_TILL_BODY()
   
   boolean LOOP_TILL_BODY()
   {
       //rule 1 
       if(list2.get(i).cp.equals("IF"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( IF_ELSE_BODY() )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if( OELSE() )
                                   {
                                       if( LOOP_TILL_BODY () )
                                       {
                                           return true;
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
       /*
   //   rule 2
       
       else if( AE() )
       {
           if(OE_dash())
           {
               if(LOOP_TILL_BODY())
               {
                   return true;
               
               }
           }
       }
       */
       //rule 3
       
       else if(list2.get(i).cp.equals("DT"))
       {
           i++;
           if( FACT5_INIT() )
           {
               return  true;
           }
       }
       
       //rule 4
       
       else if(list2.get(i).cp.equals("LOOPWHILE"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                   
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_WHILE_BODY())
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if( LOOP_TILL_BODY() )
                                   {
                                       return true;
                                   
                                   }
                               
                               
                               }
                           
                           
                           }
                       
                       }
                       }
                   }
               }
           }
       
       
       //rule 5
       
       else if(list2.get(i).cp.equals("LOOPTILL"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                   
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_TILL_BODY() )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if( LOOP_TILL_BODY() )
                                   {
                                       return true;
                                   
                                   }
                               
                               
                               }
                           
                           
                           }
                     
                       }
                       }
                   }
               }
           }
       
       //rule 6
       else if(list2.get(i).cp.equals("RETURN"))
       {
           i++;
           if( FACT4_LOOP_TILL() )
           {
               if(list2.get(i).cp.equals(":"))
               {
                   i++;
                   return true;
               }
           }
       }
       
       //rule 7
       
       else if(list2.get(i).cp.equals("BREAK"))
       {
           i++;
           if(list2.get(i).cp.equals(":"))
           {
               i++;
           
           }
           if(LOOP_TILL_BODY())
           {
               return true;
           }
       }
       
       //rule 8
       else if(list2.get(i).cp.equals("INC_DEC"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
           
           if(list2.get(i).cp.equals(":"))
           {
               i++;
               if(LOOP_TILL_BODY())
               {
                   return true;
               
               }
           
           }
           }
       }
       
       
       //rule 9
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if(FACT1_LOOP_TILL())
           {
               return true;
           
           
           }
       
       
       
       }
       
       //sel set
       
       else if(list2.get(i).cp.equals("}"))
       {
           return true;
       }
       
       
       
  
   return false;
   }
   
   //FACT1_LOOP_TILL
   
   boolean  FACT1_LOOP_TILL() 
   {
      // rule 1
       if(list2.get(i).cp.equals("@"))
       {
           i++;
           if( list2.get(i).cp.equals("Identifier_Const") )
           {
               i++;
               if( FACT3_LOOP_TILL() )
               {
                   if(LOOP_TILL_BODY())
                   {
                       return true;
                   
                   
                   }
               
               }
           
           }
       
       
       }
       
       //rule 2
       
       else if( list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT7_INIT() )
           {
               if(LOOP_TILL_BODY())
               {
                   return true;
               }
               
           }
       }
       
       /*
       
       //rule 3
       
       else if( list2.get(i).cp.equals("("))
       {
           i++;
           if( PARA() )
           {
               if( list2.get(i).cp.equals(")"))
               {
                   i++;
                   if( T_dash() )
                   {
                       if( E_dash() )
                       {
                           if( RE_dash() )
                           {
                               if( AE_dash() )
                               {
                                   if( OE_dash() )
                                   {
                                       if(LOOP_TILL_BODY())
                                       {
                                           return true;
                                       }
                                    } 
                               }
                            }
                        }
                    }
                }
           }
       }
       
       //rule 4
       else if( EXP_X() )
       {
           if( T_dash() )
           {
               if( E_dash() )
               {
                   if( RE_dash() )
                   {
                       if( AE_dash() )
                       {
                       
                           if( OE_dash() )
                           {
                           
                               if(LOOP_TILL_BODY())
                               {
                               
                                   return true;
                                }
                            } 
                        }
                    }
                }
           }
        }
       
       
       //rule 5
       
       else if(list2.get(i).cp.equals("["))
       {
           i++;
           if( EXP() )
           {
               if(list2.get(i).cp.equals("]"))
               {
                   i++;
                   if( FACT2_EXP() )
                   
                   {
                       if( T_dash() )
           
                       {
               
                           if( E_dash() )
               
                           {
                   
                               if( RE_dash() )
                   
                               {
                       
                                   if( AE_dash() )
                       
                                   {
                       
                           
                                       if( OE_dash() )
                           
                                       {
                           
                               
                                           if(LOOP_TILL_BODY())
                               
                                           {
                               
                                   
                                               return true;
                                
                                           }
                         
                                       } 
                        
                                   }
                                   
                    
                               }
                
                           }
           
                       }
                   }
               }
           }
       }
       
       //rule 6
   
       else if( T_dash() )
       {
           
               if( E_dash() )
               {
                   if( RE_dash() )
                   {
                       if( AE_dash() )
                       {
                       
                           if( OE_dash() )
                           {
                           
                               if(LOOP_TILL_BODY())
                               {
                               
                                   return true;
                                }
                            } 
                        }
                    }
                }
        }
       
       */
       
     //rule 7  
       else if(list2.get(i).cp.equals("("))
       {
           i++;
           if(PARA())
           {
               if(list2.get(i).cp.equals(")"))
               {
                   i++;
                   if(list2.get(i).cp.equals(":"))
                   {
                       i++;
                       if(LOOP_TILL_BODY())
                       {
                       return true;
                       }
                       
                   
                   }
               
               }
           
           }
       
       
       }
       
       
       // rule 8 NEW
       else if(list2.get(i).cp.equals("INC_DEC"))
       {
           i++;
           if(list2.get(i).cp.equals(":"))
           {
               i++;
               if(LOOP_TILL_BODY())
               {
                   return true;
               
               }
           
           }
       
       }
       
       //rule 9 New
       else if(ASSGN_Y())
       {
           if(FACT4_SST())
           {
               if(LOOP_TILL_BODY())
               {
                   return true;
               
               }
           
           }
       
       }
       
   return false;
   }
   
   
       
//FACT3_LOOP_TILL() 
   boolean FACT3_LOOP_TILL()
    {
        //rule 1 only  
        if(list2.get(i).cp.equals("("))
            {
                i++;
                if( PARA() )
                {
                    if(list2.get(i).cp.equals(")"))
                    {
                        i++;
                        if(list2.get(i).cp.equals(":"))
                        {
                            i++;
                            if(LOOP_TILL_BODY())
                            {
                            return true;
                        }
                        }
                    
                    }
                }
            }
        
        
        //sel set
        
        else if(list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || list2.get(i).cp.equals("RETURN") || list2.get(i).cp.equals("BREAK") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-"))
        {
            return true;
        
        }
           return false;
       
    }
   
   
   //FACT4_LOOP_TILL() 
   
   boolean FACT4_LOOP_TILL()
   {
       //only 1 rule
       if( EXP() )
       {
           return true;
       
       }
       
       //rule 2 New
       
       else if(list2.get(i).cp.equals("NEW"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               
               if(list2.get(i).cp.equals("("))
               {
                   i++;
                   if(FN_CALL_PARA())
                   {
                       if(list2.get(i).cp.equals(")"))
                       {
                           i++;
                          return true;
                       }
                   
                   }
                   
               
               }
           
           }
       
       
       }
       
   
       
       //sel set
       else if(list2.get(i).cp.equals(":"))
       {
           return true;
       }
   
   
   return false;
   }
   
   
   
   
   
   //LOOP_WHILE 
   
   boolean LOOP_WHILE()
   {
       //1 only
       if(list2.get(i).cp.equals("LOOPWHILE"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_WHILE_BODY() )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   return true;
                               }
                           }
                       }
                   }
               }
           }
        }
      
   return false;
   }
   
   //LOOP_WHILE_BODY() ()
   
   boolean LOOP_WHILE_BODY ()
   {
       //rule 1 
       if(list2.get(i).cp.equals("IF"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( IF_ELSE_BODY()  )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if( OELSE() )
                                   {
                                       if( LOOP_WHILE_BODY()  )
                                       {
                                           return true;
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
       /*
   //   rule 2
       
       else if( AE() )
       {
           if(OE_dash())
           {
               if(LOOP_WHILE_BODY() )
               {
                   return true;
               
               }
           }
       }
       */
       //rule 3
       
       else if(list2.get(i).cp.equals("DT"))
       {
           i++;
           if( FACT5_INIT() )
           {
               return  true;
           }
       }
       
       //rule 4
       
       else if(list2.get(i).cp.equals("LOOPWHILE"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                   
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_WHILE_BODY()  )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if(LOOP_WHILE_BODY() )
                                   {
                                       return true;
                                   
                                   }
                               
                               
                               }
                           
                           
                           }
                       
                       }
                       }
                   }
               }
           }
       
       
       //rule 5
       
       else if(list2.get(i).cp.equals("LOOPTILL"))
       {
           i++;
           if(list2.get(i).cp.equals("("))
           {
               i++;
               if( EXP() )
               {
                   if(list2.get(i).cp.equals(")"))
                   {
                   
                       i++;
                       if(list2.get(i).cp.equals("{"))
                       {
                           i++;
                           if( LOOP_TILL_BODY()  )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if( LOOP_WHILE_BODY() )
                                   {
                                       return true;
                                   
                                   }
                               
                               
                               }
                           
                           
                           }
                       
                       }
                       }
                   }
               }
           }
       
       //rule 6
       else if(list2.get(i).cp.equals("RETURN"))
       {
           i++;
           if( FACT4_LOOP_WHILE() )
           {
               if(list2.get(i).cp.equals(":"))
               {
                   i++;
                   return true;
               }
           }
       }
       
       //rule 7
       
       else if(list2.get(i).cp.equals("BREAK"))
       {
           i++;
           if( LOOP_WHILE_BODY()  )
           {
               return true;
           }
       }
       
       //sel set
       
       else if(list2.get(i).cp.equals("}"))
       {
           return true;
       }
       
       
       
  
   return false;
   }
   
   //FACT1_LOOP_WHILE
   
   boolean  FACT1_LOOP_WHILE() 
   {
      // rule 1
       if(list2.get(i).cp.equals("@"))
       {
           i++;
           if( list2.get(i).cp.equals("Identifier_Const") )
           {
               i++;
               if( FACT3_LOOP_WHILE() )
               {
                   if( LOOP_WHILE_BODY() )
                   {
                       return true;
                   
                   
                   }
               
               }
           
           }
       
       
       }
       
       //rule 2
       
       else if( list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT7_INIT() )
           {
               return true;
           }
       }
       /*
       //rule 3
       
       else if( list2.get(i).cp.equals("("))
       {
           i++;
           if( PARA() )
           {
               if( list2.get(i).cp.equals(")"))
               {
                   i++;
                   if( T_dash() )
                   {
                       if( E_dash() )
                       {
                           if( RE_dash() )
                           {
                               if( AE_dash() )
                               {
                                   if( OE_dash() )
                                   {
                                       if( LOOP_WHILE_BODY()  )
                                       {
                                           return true;
                                       }
                                    } 
                               }
                            }
                        }
                    }
                }
           }
       }
       
       //rule 4
       else if( EXP_X() )
       {
           if( T_dash() )
           {
               if( E_dash() )
               {
                   if( RE_dash() )
                   {
                       if( AE_dash() )
                       {
                       
                           if( OE_dash() )
                           {
                           
                               if(LOOP_WHILE_BODY() )
                               {
                               
                                   return true;
                                }
                            } 
                        }
                    }
                }
           }
        }
       
       
       //rule 5
       
       else if(list2.get(i).cp.equals("["))
       {
           i++;
           if( EXP() )
           {
               if(list2.get(i).cp.equals("]"))
               {
                   i++;
                   if( FACT2_EXP() )
                   
                   {
                       if( T_dash() )
           
                       {
               
                           if( E_dash() )
               
                           {
                   
                               if( RE_dash() )
                   
                               {
                       
                                   if( AE_dash() )
                       
                                   {
                       
                           
                                       if( OE_dash() )
                           
                                       {
                           
                               
                                           if( LOOP_WHILE_BODY() )
                               
                                           {
                               
                                   
                                               return true;
                                
                                           }
                         
                                       } 
                        
                                   }
                                   
                    
                               }
                
                           }
           
                       }
                   }
               }
           }
       }
       
       //rule 6
   
       else if( T_dash() )
       {
           
               if( E_dash() )
               {
                   if( RE_dash() )
                   {
                       if( AE_dash() )
                       {
                       
                           if( OE_dash() )
                           {
                           
                               if(LOOP_WHILE_BODY() )
                               {
                               
                                   return true;
                                }
                            } 
                        }
                    }
                }
        }
       */
       
       // new rule for both looptill and loopwhile
       else if(list2.get(i).cp.equals("("))
       {
           i++;
           if(PARA())
           {
               if(list2.get(i).cp.equals(")"))
               {
                   i++;
                   if(list2.get(i).cp.equals(":"))
                   {
                       i++;
                       return true;
                   
                   }
               
               }
           
           }
       
       
       
       }
       
   return false;
   }
   
   
       
//FACT3_LOOP_WHILE() 
   boolean FACT3_LOOP_WHILE()
    {
            if(list2.get(i).cp.equals("("))
            {
                i++;
                if( PARA() )
                {
                    if(list2.get(i).cp.equals(")"))
                    {
                        i++;
                        if(list2.get(i).cp.equals(":"))
                        {
                            i++;
                            return true;
                        }
                    }
                }
            }
       
        
        //sel set
        
        else if(list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("LOOPTILL") || list2.get(i).cp.equals("LOOPWHILE") || 
list2.get(i).cp.equals("RETURN") || list2.get(i).cp.equals("BREAK") || list2.get(i).cp.equals("String_Const") || list2.get(i).cp.equals
("Char_Const") || list2.get(i).cp.equals("Integer_Const") || list2.get(i).cp.equals("Float_Const") || list2.get(i).cp.equals("!") || 
list2.get(i).cp.equals("+") || list2.get(i).cp.equals("-"))
        {
            return true;
        
        }
           return false;
       
    }
   
   
   //FACT4_LOOP_WHILE() 
   
   boolean FACT4_LOOP_WHILE()
   {
       //only 1 rule
       if( EXP() )
       {
           return true;
       
       } 
       
       //sel set
       else if(list2.get(i).cp.equals(":"))
       {
           return true;
       
       
       }
   
   
   return false;
   }
   
   //init cfg
   //FACT1_INIT()
   /*
   boolean FACT1_INIT()
   {
       //only rule 1
       if(list2.get(i).cp.equals("["))
       {
           i++;
           if(list2.get(i).cp.equals("]"))
           {
               i++;
               if( FACT2_INIT() )
               {
                   return true;
               
               }
           
           }
        }
       
       //sel set
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           return true;
       }
       
   return false;
   }
   */
   //FACT2_INIT()
   boolean FACT2_INIT()
   {
       //only 1 rule
       if(list2.get(i).cp.equals("["))
       {
           i++;
           if(list2.get(i).cp.equals("]"))
           {
               i++;
           }
       }
   
   //sel set
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           return true;
       }
   
   return false;
   }
   
    //FACT3_INIT()
   boolean  FACT3_INIT()
   {
       //rule 1
       if(list2.get(i).cp.equals("{") )
       {
           i++;
           if( EXP() )
           {
               if( INIT_C1() )
               {
                   if(list2.get(i).cp.equals("}"))
                   {
                       i++;
                       if(INIT_X2 () )
                       {
                           return true;
                       
                       }
                   
                   
                   }
               
               
               }
           
           
           }
       
       }
       
       //rule 2
       
       else if(list2.get(i).cp.equals("NEW") )
       {
           i++;
           if( INIT_DT2 () )
           {
               if(list2.get(i).cp.equals("["))
               {
                   i++;
                   if(EXP ())
                   {
                       if(list2.get(i).cp.equals("]"))
                       {
                           i++;
                           if(INIT_X2 () )
                           {
                               return true;
                           }
                       }
                   }
               }
           }
       }
       
       //rule 3
       
       else if(list2.get(i).cp.equals("NULL") )
       {
           i++;
           if(INIT_X2 () )
           {
               return true;
           }
       }
   
       return false;
   }
   
   
  //FACT4_INIT() 
   
   boolean FACT4_INIT()
   {
       //rule 1
       if(list2.get(i).cp.equals("{"))
       {
           i++;
           if(list2.get(i).cp.equals("{"))
           {
               i++;
               if( EXP() )
               {
                   if(INIT_C1 () )
                   {
                       if(list2.get(i).cp.equals("}"))
                       {
                           i++;
                           if(INIT_C2 () )
                           {
                               if(list2.get(i).cp.equals("}"))
                               {
                                   i++;
                                   if(INIT_X3 () )
                                   {
                                       return true;
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
   
       
       //rule 2
       else if(list2.get(i).cp.equals("NEW"))
       {
           i++;
           if(INIT_DT2 () )
           {
               if(list2.get(i).cp.equals("["))
               {
                   i++;
                   if(EXP () )
                   {
                       if(list2.get(i).cp.equals("]"))
                       {
                           i++;
                           if(list2.get(i).cp.equals("["))
                           {
                               i++;
                               if(EXP()  )
                               {
                                   if(list2.get(i).cp.equals("]"))
                                   {
                                       i++;
                                       if(INIT_X3() )
                                       {
                                           return true;
                                       }
                                   
                                   }
                               
                               }
                           
                           }
                       
                       
                       }
                   }
               
               }
           
           }
           
       
       
       }
       //waris start
       // rule 3
       else if(list2.get(i).cp.equals("NULL"))
       {
           i++;
           if(INIT_X3 () ) 
           {
           return true;
           }
       }
   
   return false;
   }
  
   
   //FACT5_INIT()
   
   boolean FACT5_INIT()
   {
       //rule 1
       if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           if( FACT9_INIT() )
           {
               return true;
           }
           
           
           /*
           //rule 2
           else if(list2.get(i).cp.equals("["))
           {
               i++;
               if(list2.get(i).cp.equals("]"))
                       {
               if(list2.get(i).cp.equals("Identifier_Const"))
               {
                   i++;
                   if(list2.get(i).cp.equals("["))
                   {
                        i++;
                        if(list2.get(i).cp.equals("]"))
                        {
                            i++;
                            if(FACT6_INIT())
                            {
                                return true;
                            }
                        }
                   
                   }
               }
           }
        }
           */
       }
       
   return false;
   }
   
   
   //FACT6_INIT
   boolean FACT6_INIT()
   {
       if(list2.get(i).vp.equals("["))
       {
           i++;
           if(list2.get(i).vp.equals("]"))
           {
               i++;
               if(INIT_LIST3())
               {
                   return true;
               }
           }
       }
       
       //rule 2
       
       else if(INIT_LIST2())
       {
           return true;
       }
       return false;
   }
   
   //FACT7_INIT
   
   boolean FACT7_INIT()
   {
       
       //rule1
       if(INIT_LIST4())
       {
            return true;
       }
       
       //rule 2
       else if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(FACT8_INIT())
                       {
                           return true;
                       }
                   }
               }
       
       
   return false;
   }
   
   // FACT8_INIT
   
   boolean FACT8_INIT()
   {
       
       //rule1
       if(INIT_LIST2())
       {
            return true;
       }
       
       //rule 2
       else if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(INIT_LIST3())
                       {
                           return true;
                       }
                   }
               }
       
       
   return false;
   }
   
   
   //FACT9_INIT()
   boolean FACT9_INIT()
   {
       //rule 1
       if(list2.get(i).vp.equals("["))
        {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(FACT6_INIT())
                       {
                           return true;
                       
                       }
                   }
       }
       
       else if(INIT_LIST1())
       {
           return true;
       
       
       }
       
   
   
   return false;
   }
   
   boolean FACT10_INIT()
   {
       //rule 1
       if(list2.get(i).cp.equals("NULL"))
       {
           i++;
           if(INIT_X4())
           {
               return true;
           
           }
       
       
       }
       
      // rule 2
       
       
          else if(list2.get(i).cp.equals("NEW"))
           {
               i++;
               if(list2.get(i).cp.equals("Identifier_Const"))
               {
                   i++;
                   if(list2.get(i).vp.equals("("))
                   {
                       i++;
                           if(FN_CALL_PARA())
                           {
                               if(list2.get(i).vp.equals(")"))
                               {
                                   i++;
                                   if( INIT_X4() )
                                   {
                                      
                                       return true;
                                   }
                               }
                           }
                       
                   }
               }
   
           }
   return false;
   }
   //INIT_LIST2
   
   boolean INIT_LIST2()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(INIT_LIST1())
                       {
                           return true;
                       }
                   }
               }
           }
       }
       
       //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       }
       //rule 3
       else if(list2.get(i).cp.equals("="))
       {
           i++;
           if(FACT3_INIT())
           {
               return true;
           }
       }
       
       return false;
   }
   
   //INIT_LIST3
   
   boolean INIT_LIST3()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(list2.get(i).vp.equals("["))
                       {
                           i++;
                           if(list2.get(i).vp.equals("]"))
                           {
                               i++;
                               if(INIT_LIST3())
                               {
                                    return true;
                               }
                           }   
                       }
                       
                   }
               }
           }
       }
       
       //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       }
       //rule 3
       else if(list2.get(i).cp.equals("="))
       {
           i++;
           if(FACT4_INIT())
           {
               return true;
           }
       }
       
       return false;
   }
   
   // INIT_LIST1
   
   boolean INIT_LIST1()
   {
       //rule 1
       if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       }
       
       //rule 2
       else if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
           
           
           
               if(INIT_LIST1())
           
               {
                 
               
                   return true;
           
               }
           }
       }
       
       //rule 3
       else if(list2.get(i).cp.equals("="))
       {
           i++;
           if(EXP())
           {
               if(INIT_X1())
               {
                   return true;
               }
           }
       }
       
       return false;
   }
   
   // LIST4_INIT

   boolean INIT_LIST4()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(INIT_LIST4())
               {
                   return true;
               }
           }
       }
       
       //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       }
       //rule 3
       else if(list2.get(i).cp.equals("="))
       {
           i++;
           if(FACT10_INIT() )
           {
           /*
               if(list2.get(i).cp.equals("NEW"))
           {
               i++;
               if(list2.get(i).cp.equals("Identifier_Const"))
               {
                   i++;
                   if(list2.get(i).vp.equals("("))
                   {
                       i++;
                       if(EXP())
                       {
                           if(INIT_C1())
                           {
                               if(list2.get(i).vp.equals(")"))
                               {
                                   i++;
                                   if( INIT_X4() )
                                   {
                                       i++;
                                       return true;
                                   }
                               }
                           }
                       }
                   }
                   
               }
           }*/
       return true;
           }
           
       }
       
       return false;
   }
   
   //INIT_X1
   
   boolean INIT_X1()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(INIT_LIST1())
               {
                   return true;
               
               }
           
           }
       }
   
   //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
   return false;
   }
   
   //INIT_X2()
   boolean INIT_X2()
   {
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(INIT_LIST2() )
                       {
                           return true;
                       
                       }
                   
                   }
               
               }
           }
       }
       
   //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
   return false;
   }
   
   //INIT_X3
   
   boolean INIT_X3()
   {
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(list2.get(i).vp.equals("]"))
                   {
                       i++;
                       if(list2.get(i).vp.equals("["))
               
                       {
                   
                           i++;
                   
                           if(list2.get(i).vp.equals("]"))
                            {
                       
                               i++;
                               if( INIT_LIST3() )
                               {
                                   return true;
                               
                               }
                            }
                        }
                   }
               }
           }
       }
       
   //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
   return false;
   }
   
     //INIT_X4
   
   boolean INIT_X4()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(INIT_LIST4())
               {
                   return true;
               
               }
           
           }
       }
   
   //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
   return false;
   }
   
   

    //INIT_C1()
   
   
   boolean INIT_C1()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if( EXP() )
           {
               if(INIT_C1())
               {
                   return true;
               
               }
           
           }
       }
       
   //sel set
       else if(list2.get(i).vp.equals("}"))
       {
           return true;
       }
       
   
   return false;
   }
   
   //INIT_C2()
   
   
   boolean INIT_C2()
   {
       
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if(list2.get(i).vp.equals("{"))
           {
               i++;
               if( EXP() )
               {
               
                   if(INIT_C1())
                   {
                       if(list2.get(i).vp.equals("}"))
                       {
                           i++;
                           if( INIT_C2() )
                           {
                               return true;
                           
                           }
                       
                       }
                    }
                }
           }
       }
       
   //sel set
       else if(list2.get(i).vp.equals("}"))
       {
           return true;
       }
       
   return false;
   }
   
  // INIT_DT2()
   boolean  INIT_DT2()
   {
       if(list2.get(i).cp.equals("DT"))
       {
           i++;
           return true;
       }
       
       //rule 2
       else if(list2.get(i).cp.equals("Identifier_Const"))
       {
           i++;
           return true;
       
       
       }
   
   
   return false;
   }
   
   
   
   //ASSGNMNET cfg start
   
   boolean ASSGN_FACT1()
   {
       //rule 1
       if( ASSGN_Y() )
       {       
           
           /*
       if(list2.get(i).cp.equals("="))
       {
           i++;*/
           if( ASSGN_FACT2() )
           {
               return true;
           
           }
       
          
         }
       //rule 2
       else if(list2.get(i).vp.equals("["))
       {
           i++;
           if( EXP() )
           {
               if(list2.get(i).cp.equals("]"))
               {
                   i++;
                   if( ASSGN_FACT3() )
                   {
                       return true;
                   
                   }
               
               }
           
           }
       
       }
       
     
   
   
   
   return false;
   }
   
   //FACT2_ASSGN
   
   boolean ASSGN_FACT2()
   {
       //rule 1
       
       if(EXP () )
       {
           if(ASSGN_LIST() )
           {
               return true;
           
           }
       
       }
       
       //rule 2
       else if(ASSGN_Y())
       {
           /*
        if(list2.get(i).cp.equals("="))
       {
           i++;

           */if(ASSGN_FACT6())
           {
               return true;
           
           
           }
           /*
           if(list2.get(i).cp.equals("NEW"))
           {
               i++;
               if(list2.get(i).cp.equals("Identifier_Const"))
               {
                   i++;
                   if(list2.get(i).vp.equals("("))
                   {
                       i++;
                       if(ASSGN_PARA())
                       {
                           if(list2.get(i).vp.equals(")"))
                           {
                               i++;
                               if( ASSGN_LIST2() )
                               {
                                   return true;
                               
                               }
                           
                           }
                       
                       }
                   
                   }
               
               }
               
           
           }
       */
       
   }
   return false;
   }
   
   //ASSGN_FACT3
   
   
   boolean ASSGN_FACT3()
   {
       //rule 1
       if(ASSGN_Y())
       {
           /*
       if(list2.get(i).cp.equals("="))
       {
           i++;
           */
           if(ASSGN_FACT4())
           {
               return true;
           
           
           }
           /*
           if( EXP() )
           {
               if( ASSGN_LIST3() )
               {
                   return true;
               
               }
           
           }
           */
       
       }
       //rule 2
       
       else if(list2.get(i).vp.equals("[") )
       {
           i++;
           if(EXP())
           {
               if(list2.get(i).vp.equals("]"))
               {
                   i++;
                   if(ASSGN_Y())
                   {
                   /*
                       if(list2.get(i).vp.equals("="))
                   {
                       i++;
*/                       if(ASSGN_FACT5())
                       {
                           return true;
                       
                       }
                       /*
                       if(EXP())
                       {
                           if( ASSGN_LIST4() )
                           {
                               return true;
                           }
                       }*/
                   }
               }}
           
           }
       
   return false;
   }
   
   //ASSGN_FACT4()
   
   
   boolean ASSGN_FACT4()
   {
       //rule 1
       if(EXP() )
       {
           if(ASSGN_LIST3())
           {
               return true;
           
           }
       
       }
       
       //rule 2
       else if(list2.get(i).cp.equals("NULL"))
       {
           i++;
           if(ASSGN_LIST3())
           {
               return true;
           
           }
       
       
       }
   
   
   
   return false;
   }
   
   
   boolean ASSGN_FACT5()
   {
       //rule 1
       if(EXP() )
       {
           if(ASSGN_LIST4())
           {
               return true;
           
           }
       
       }
       
       //rule 2
       else if(list2.get(i).cp.equals("NULL"))
       {
           i++;
           if(ASSGN_LIST4())
           {
               return true;
           
           }
       
       
       }
   
   
   
   return false;
   }
   
   
  //ASSGN_FACT6()
   
   boolean ASSGN_FACT6()
   {
       //rule 1
       if(list2.get(i).cp.equals("NEW"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).cp.equals("("))
               {
                   i++;
                   if(ASSGN_PARA())
                   {
                       if(list2.get(i).cp.equals(")"))
                       {
                           i++;
                           if(ASSGN_LIST2())
                           {
                               return true;
                           
                           
                           }
                       
                       }
                   
                   }
               
               }
           
           }
           
        
           
       //rule 2
       else if(list2.get(i).cp.equals("NULL"))
       {
           i++;
           if(ASSGN_LIST2())
           {
               return true;
           
           }
       
       
       }
           
       
       
       }
   
       
       
   return false;
   }
   
   
   //ASSGN_LIST
   
   boolean ASSGN_LIST()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
              if(ASSGN_Y()) 
              {
                  /*
               if(list2.get(i).cp.equals("="))
               {
                   i++;*/
                   if( EXP() )
                   {
                       if(ASSGN_LIST())
                       {
                           return true;
                       
                       }
                   
                   }
               
               }
           
           } 
       
       }
      
       
       //rule 2
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
       
       
   return false;
   }
   
   //ASSGN_LIST2
   
   
   boolean ASSGN_LIST2()
   {
       
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
              if(ASSGN_Y()) 
              {
                  /*
               if(list2.get(i).cp.equals("="))
               {
                   i++;*/
                   if(ASSGN_FACT6())
                   {
                       return true;
                   }
                   
                   }
                  /* 
                   if(list2.get(i).cp.equals("NEW"))
                   {
                       i++;
                       if(list2.get(i).cp.equals("Identifier_Const"))
                       {
                           i++;
                           if(list2.get(i).vp.equals("("))
                           {
                               i++;
                               if(ASSGN_PARA())
                               {
                                   if(list2.get(i).vp.equals(")"))
                                   {
                                       i++;
                                       if(ASSGN_LIST2())
                                       {
                                           return true;
                                       
                                       }
                                   
                                   }
                               
                               }
                           }
                               
                       
                       }
                       
                   }
                   
               }*/
           }
           
       }
   
       //rule 2
       
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
           return true;
       
       }
   
   return true;
   }
   
 
   
   //ASSGN_LIST()
   
   boolean ASSGN_LIST3()
   {
       
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(EXP() )
                   {
                       if( list2.get(i).vp.equals("]") )
                       {
                           i++;
                        if(ASSGN_Y())  
                        {
                            /*
                           if(list2.get(i).vp.equals("="))
                           {
                               i++;
*/                               
                               if(ASSGN_FACT4())
                               {
                                   return true;
                               
                               
                               }
                               /*
                               if( EXP() )
                               {
                                   if(ASSGN_LIST3())
                                   {
                                       return true;
                                   
                                   }
                               
                               }*/
                           
                           }
                       
                       }
                   
                   }
               
               }
           }
        }
       
       //rule 2
       
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
       
           return true;
       } 
   
   
   return false;
   }
   
   
   
   //ASSGN_LIST4()
   
   boolean ASSGN_LIST4()
   {
       //rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               if(list2.get(i).vp.equals("["))
               {
                   i++;
                   if(EXP() )
                   {
                       
                       if( list2.get(i).vp.equals("]") )
                       {
                           
                           i++;
                           if(list2.get(i).vp.equals("["))
                            {
                   
                               i++;
                   
                               if(EXP() )
                                {
                       
                                   if( list2.get(i).vp.equals("]") )
                                    {
                           
                                       i++;
                                       if(ASSGN_Y())
                                       {
                                           /*
                                       if(list2.get(i).vp.equals("="))
                                        {
                               
                                           i++;*/
                                           if(ASSGN_FACT5())
                                           {
                                           return true;
                                           }
                                        
                               /*
                                           if( EXP() )
                                            {
                                   
                                               if(ASSGN_LIST4())
                                                {
                                       
                                                   return true;
                                                }
                                            }*/
                                        }
                                    }
                                }
                            }
                       }
                   }
               }
           }
       }
       
       //rule 2
       
       else if(list2.get(i).vp.equals(":"))
       {
           i++;
       
           return true;
       } 
   
       
       return false;
   }
   
   //ASSGN_Y()

   boolean ASSGN_Y()
   {
       //rule 1
       if(list2.get(i).cp.equals("ASOP"))
       {
           i++;
           return true;
           
       }
       
       else if(list2.get(i).cp.equals("="))
       {
           i++;
           return true;
       
       }
   return false;
   
   
   
   }
   //ASSGN_PARA()
   boolean ASSGN_PARA()
   {
       // only rule 1
       
       if( EXP() )
       {
           if( ASSGN_C1() )
           {
               return true;
           }
       }
       
       //sel set
       
       else if(list2.get(i).vp.equals(")"))
       {
           
           return true;
       }
       
   return false;
   }
   
   //ASSIG C1()
   
   boolean ASSGN_C1()
   {
       //only rule 1
       if(list2.get(i).vp.equals(","))
       {
           i++;
           if( EXP() )
            {
               if( ASSGN_C1() )
               {
               
                   return true;
                }
            }
       }
       
       //sel set
       
       else if(list2.get(i).vp.equals(")"))
       {
           
           return true;
       }
       
   return false;
   }
   
   
   
   
   
   
//CLASS CFG
//S_Class() rule
boolean S_Class()
{
//rule 1 only
	
	if(AM())
	{
		if(list2.get(i).cp.equals("MODEL"))
		{
			i++;
			if(list2.get(i).cp.equals("Identifier_Const"))
			{
				i++;
				if(list2.get(i).vp.equals("{"))
				{
					i++;
					if( CLASS_X() )
					{
						if(list2.get(i).vp.equals("}"))
						{
							i++;
							return true;
						}
					}						
				}		
			}
		}
	}
	return false;
}

//AM rule

boolean AM()
{
	if(list2.get(i).cp.equals("ACCESSMODIFIER") )
	{
		i++;
		return true;

	}
	return false;
}

//CLASS_X() rule
boolean CLASS_X()
{
    //rule 1
	if(list2.get(i).cp.equals("DT"))
	{
		i++;
		if(FACT5_INIT())
		{
                    if(CLASS_X())
                    {
                        return true;
                    }
				
		}
	}
        
        //rule 2
	else if(list2.get(i).cp.equals("Identifier_Const"))
	{
		i++;
		if(CLASS_FACT2())
		{
                    if(CLASS_X())
                    {
                    return true;
                    }
			
		}
	}
        
        //rule 3
	else if(AM())
	{
		if(CLASS_FACT1())
		{
                    if(CLASS_X())
                    {
                     return true;
                    }
			
		}
	}

        //sel set
        else if(list2.get(i).cp.equals("}"))
        {
            return true;
        
        
        }



	return false;
}

//CLASS_FACT2() rule
boolean CLASS_FACT2()
{
  /*  
	//rule 1
	if(list2.get(i).vp.equals("="))
	{
		i++;
		if(CLASS_FACT3())
		{
			return true;
		}
	}
*/
    //New rule 1
    if(ASSGN_FACT2())
    {
        return true;
    }
    
    
    
    
//rule 2
	else if(list2.get(i).vp.equals("["))
	{
		i++;
		if(CLASS_FACT4())
		{
			return true;
		}
		
	}

//rule 3
	else if(list2.get(i).vp.equals(","))
	{
		i++;
		if(list2.get(i).cp.equals("Identifier_Const"))
		{
			i++;
			if( INIT_LIST4() )
			{
				return true;
			}
		}
	}
/*
//rule 4
	else if(list2.get(i).vp.equals(":"))
	{
		i++;
		return true;
	}

*/
    // New rule 5
        else if(list2.get(i).cp.equals("Identifier_Const"))
        {
            i++;
            if(FACT7_INIT())
            {
                return true;
            
            }
        
        
        
        
        }
        
        
	return false;
}

//CLASS_FACT1() rule

boolean CLASS_FACT1()
{
//rule 1
	if(list2.get(i).cp.equals("MODEL"))
	{
		i++;
		if(list2.get(i).cp.equals("Identifier_Const"))
		{
			i++;
			if(list2.get(i).vp.equals("{"))
			{
				i++;
				if(CLASS_X())
				{
					if(list2.get(i).vp.equals("}"))
					{
						i++;
						return true;
					}
														
				}

			}
		}
	}


//rule 2
	else if(FN_CREAT_FACT1())
	{
		return true;	
	}
	

	return false;
}


/*
//CLASS_FACT3() rule
boolean CLASS_FACT3()
{
    
//rule 1
	if(list2.get(i).cp.equals("NEW"))
	{
		i++;
		if(list2.get(i).cp.equals("Identifier_Const"))
		{
			i++;
			if(list2.get(i).vp.equals("("))
			{
				i++;
				if(FN_CALL_PARA())
                                {
                                    if(list2.get(i).vp.equals(")"))
						{
							i++;
							if(INIT_X4())
							{
								return true;
							}
						}
					
                                }
			}

		}	
	}

//rule 2
	else if(ASSGN_FACT2())
	{
		return true;
	}	

	return false;
}
*/
//CLASS_FACT4()
boolean CLASS_FACT4()
{
//rule 1
	if(list2.get(i).vp.equals("]"))
	{
		i++;
		if(FACT8_INIT())
		{
			return true;
		}	
	}
	else if( EXP() )
	{
		if(list2.get(i).vp.equals("]"))
		{
			i++;
			if(ASSGN_FACT3())
			{
				return true;
			}				
		}	
	}


	return false;
}

//FN CALL CFG

// FN_CALL_S rule

boolean FN_CALL_S()
{
//rule 1
	if(list2.get(i).cp.equals("Identifier_Const"))	
	{
		i++;
		if(FN_CALL_FACT1())
		{
			return true;
		}
	}	

	return false;
}


boolean FN_CALL_FACT1()
{
//rule 1
	if(list2.get(i).cp.equals("@"))
	{
		i++;
		if(list2.get(i).cp.equals("Identifier_Const"))
		{
			i++;
			if(list2.get(i).vp.equals("("))
			{
				i++;			
				if(FN_CALL_PARA())
				{
					if(list2.get(i).vp.equals(")"))
					{
						i++;
						return true;
					}
				}	
			}		
		}
	}

//rule 2
	else if(list2.get(i).vp.equals("["))
	{
		i++;
		if(EXP())
		{
			if(list2.get(i).vp.equals("]"))
			{
				i++;
				if(list2.get(i).cp.equals("@"))
				{
					i++;
					if(list2.get(i).cp.equals("Identifier_Const"))
					{
						i++;		
						return true;
					}
				}
			}
		}	
	}
//rule 3
	else if(list2.get(i).vp.equals("("))
	{
		i++;
		if(FN_CALL_PARA())
		{
			if(list2.get(i).vp.equals(")"))
			{
				i++;
				return true;
			}
		}
	}		


	return false;
}

//FN_CALL_PARA() rule
boolean FN_CALL_PARA()
{
//rule 1
	if(EXP())
	{
		if(FN_CALL_LIST())
		{
			return true;	
		}
	}

	//sel set
	else if(list2.get(i).vp.equals(")"))	
	{
		return true;
	}

	return false;
}

//FN_CALL_LIST() rule
boolean FN_CALL_LIST()
{
//rule 1
	if(list2.get(i).vp.equals(","))
	{	i++;
		if(EXP())
		{
			if(FN_CALL_LIST() )
			{
				return true;	
			}
		}
	}

		//sel set
		else if(list2.get(i).vp.equals(")"))	
		{
			return true;
		}

	
	return false;
}

//FN_CREAT CFG

// FN_CREAT_S() rule
boolean FN_CREAT_S()
{
	if(AM())
	{
		if(FN_CREAT_FACT1())
		{
			return true;
		}
		
	}
	
	return false;
}


//FN_CREAT_FACT1() rule
boolean FN_CREAT_FACT1()
{
	if(FN_CREAT_DT())
	{
		if(list2.get(i).cp.equals("PROCESS"))
		{
			i++;
			if(list2.get(i).cp.equals("Identifier_Const"))
			{
				i++;
				if(list2.get(i).vp.equals("("))
				{
					i++;
					if(FN_CREAT_PARA() )
					{
						if(list2.get(i).vp.equals(")"))
						{
							i++;
							if(list2.get(i).vp.equals("{"))
							{
								i++;
								if(FN_CREAT_BODYDT())
								{
									if(list2.get(i).vp.equals("}"))
									{
										i++;
										return true;
									}
									
								}
									
							}
						}
					}
				}				
			}

		}
	}

//rule 2
	else if(list2.get(i).cp.equals("VOID"))
	{
		i++;
		if(list2.get(i).cp.equals("PROCESS"))
		{
			i++;
			if(list2.get(i).cp.equals("Identifier_Const"))
			{
				i++;
				if(list2.get(i).vp.equals("("))
				{
					i++;
					if(FN_CREAT_PARA())
					{
						if(list2.get(i).vp.equals(")"))
						{
							i++;
							if(list2.get(i).vp.equals("{"))
							{
								i++;
								if(FN_CREAT_BODYV())
								{
									if(list2.get(i).vp.equals("}"))
									{
										i++;
										return true;
									}	
								}
							}	
						}
					}	
				}					
			}
		}	
	}
	return false;
}

//FN_CREAT_BODYDT() rule

boolean FN_CREAT_BODYDT()
{
	if( MST() )
	{
		if(FN_CREAT_X())
		{
			return true;	
		}
	}	

	return false;
}

//FN_CREAT_BODYV rule

boolean FN_CREAT_BODYV()
{
	if(MST())
	{
		if(FN_CREAT_X())
		{
			return true;	
		}
	}	

	return false;
}


//FN_CREAT_X rule
boolean FN_CREAT_X()
{
	if(list2.get(i).cp.equals("RETURN"))
	{
		i++;
		if(FN_CREAT_FACT4())
		{
			if(list2.get(i).vp.equals(":"))
			{
				i++;
				return true;
			}
		}
	}
//sel set
	else if(list2.get(i).vp.equals("}"))
	{
		return true;
	}
	
	return false;
}

//FN_CREAT_FACT4() rule
boolean FN_CREAT_FACT4()
{
    //only 1 rule
	if(EXP())
	{
		return true;
	}
        
        
       //rule 2 New
       
       else if(list2.get(i).cp.equals("NEW"))
       {
           i++;
           if(list2.get(i).cp.equals("Identifier_Const"))
           {
               i++;
               
               if(list2.get(i).cp.equals("("))
               {
                   i++;
                   if(FN_CALL_PARA())
                   {
                       if(list2.get(i).cp.equals(")"))
                       {
                           i++;
                          return true;
                       }
                   
                   }
                   
               
               }
           
           }
       
       
       }
       
   

//sel set
	else if(list2.get(i).vp.equals(":"))
	{
            
	return true;
		
	}
	return false;
}


//PARA_FN_CREAT rule
boolean FN_CREAT_PARA()
{
//rule 1
	if(FN_CREAT_DT())
	{
		if(list2.get(i).cp.equals("Identifier_Const"))
		{
			i++;
                        if(FN_CREAT_FACT2())
                        {
                            if(FN_CREAT_LIST())
                            {
				return true;
                            }
		        }
                }
	}
//sel set	
	else if(list2.get(i).vp.equals(")"))	
	{
		return true;
	}

	return false;	
}

//FN_CREAT_LIST() rule
boolean FN_CREAT_LIST()
{
	if(list2.get(i).vp.equals(","))
	{
		i++;
		if(FN_CREAT_DT())
		{
			if(list2.get(i).cp.equals("Identifier_Const"))
			{
				i++;
                                
                                if(FN_CREAT_FACT2())
                                {
                                    if(FN_CREAT_LIST())
                                    {
					return true;
                                    }
                                }
			}
		}	
		
	}
	

//sel set
	else if(list2.get(i).vp.equals(")"))	
		{
			return true;
		}

	return false;	
}

//FN_CREATE_FACT2() rule
boolean FN_CREAT_FACT2()
{
	if(list2.get(i).vp.equals("["))
	{
		i++;
		if(list2.get(i).vp.equals("]"))
		{
			i++;
			if(FN_CREAT_FACT3())
			{
				return true;
			}
		}		
	}

	//sel set
	else if(list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("PROCESS") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals(")") )
	{
		return true;
	}

	return false;
}

//FN_CREATE_FACT3() rule
boolean FN_CREAT_FACT3()
{
	if(list2.get(i).vp.equals("["))
	{
		i++;
		if(list2.get(i).vp.equals("]"))
		{
			i++;
			return true;
		}		
	}
	
//sel set
	else if(list2.get(i).cp.equals("Identifier_Const") || list2.get(i).cp.equals("PROCESS") || list2.get(i).cp.equals(",") || list2.get(i).cp.equals(")") )
	{
		return true;
	}

	return false;
}

//FN_CREAT_DT() rule
boolean FN_CREAT_DT()
{
	if(list2.get(i).cp.equals("DT"))	
	{
		i++;
		if(FN_CREAT_FACT2 ())
		{
			return true;
		}
	}

	else if(list2.get(i).cp.equals("Identifier_Const"))
	{
		i++;
                if(FN_CREAT_FACT2())
                {
                return true;
                }
		
	}

	return false;
}
   
}
