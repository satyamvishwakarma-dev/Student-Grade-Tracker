public class Student {
    String name;
    int rollNo;
    int[] marks;

    public Student(String name,int rollNo,int[] marks){
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public double getTotal(){
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    public double getAvg(){
        return getTotal()/marks.length;
    }

    public String getGrade(){
        double avg = getAvg();
        if (avg>=90) return "A+";
        else if (avg>=80) return "A-";
        else if (avg>=70) return "B+";
        else if (avg>=60) return "B-";
        else if (avg>=50) return "C+";
        else if (avg>=40) return "C-";
        else return "D";
    }

    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(rollNo);
        for (int i = 0; i < marks.length; i++) {
            sb.append(",").append(marks[i]);
        }
        return sb.toString();
    }

}
