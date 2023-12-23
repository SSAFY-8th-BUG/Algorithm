import java.util.*;

class Solution {
    static int answer, minA, minB;
    static List<Integer> modA, modB;
    public int solution(int[] arrayA, int[] arrayB) {
        answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        minA=arrayA[0];
        minB=arrayB[0];
        modA = new ArrayList<>();
        modB = new ArrayList<>();
        getMods(minA, modA);
        getMods(minB, modB);
        getAllMods(modA, arrayA);
        getAllMods(modB, arrayB);
        check(modA, arrayB);
        check(modB, arrayA);
        return answer;
        
    }
    void getMods(int min, List<Integer> mod){ //가장 작은 수의 mods 구하기
        for(int i=2; i<=min; i++){
            if(min%i==0) mod.add(i);
        }
        //System.out.println(mod);
    }
    
    void getAllMods(List<Integer>mod, int[] array){ //배열의 mods 구하기
        for(int num : array){
            for(int i=mod.size()-1; i>=0; i--){
                if(num%mod.get(i)!=0){
                    mod.remove(i);
                }
            }
        }
        //System.out.println(mod);
    }
    
    void check(List<Integer>mod, int[] array){ //다른 배열에 나눌 수 없는 수 구하기
        for(int i=mod.size()-1; i>=0; i--){
            int m=mod.get(i);
            if(m<=answer)return;
            boolean t=true;
            for(int num : array){
                if(num%m==0){
                    t=false;
                    break;
                }
            }
            if(t)answer = m;
        }
    }
}