
package loon.physics;

public class PInsertionSorter
{

    public static final void sort(PSortableObject objs[], int num)
    {
        if(num == 1){
            return;
        }
        for(int i = 1; i < num; i++)
        {
            PSortableObject s = objs[i];
            if(objs[i - 1].value > s.value)
            {
                int j = i;
                do{
                    objs[j] = objs[j - 1];
                }while(--j > 0 && objs[j - 1].value > s.value);
                objs[j] = s;
            }
        }

    }

    private PInsertionSorter()
    {
    }
}
