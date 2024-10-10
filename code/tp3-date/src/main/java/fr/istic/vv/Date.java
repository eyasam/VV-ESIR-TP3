package fr.istic.vv;

class Date implements Comparable<Date> {

    private int m_day;
    private int m_month;
    private int m_year;

    /**
     * Constructs a Date object with the specified day, month, and year.
     * @param day   the day of the month 
     * @param month the month of the year 
     * @param year  the year 
     */
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.m_day = day;
        this.m_month = month;
        this.m_year = year;
    }

    
    /**
     * Checks if a given date is valid.
     *
     * @param day   the day of the month
     * @param month the month of the year
     * @param year  the year
     * @return true if the date is valid and false otherwise
     */
    public static boolean isValidDate(int day, int month, int year) {
    	
        if ((month<=0)||(month>12)) {
        	return false;
        }
        
        int daysInMonth = getDaysInMonth(month, year);
        
        if ((day<1)||(day>daysInMonth)) {
        	return false;
        }
        return true;
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return ((year%4==0) && (year%100!=0)) || (year %400==0);
    }

    /**
     * Returns the next date after this date.
     * @return a new Date object representing the next date
     */
    public Date nextDate() {
    	
        int nextDay=m_day+1;
        int nextMonth = m_month;
        int nextYear = m_year;

        if (nextDay > getDaysInMonth(m_month, m_year)) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth>12) {
                nextMonth=1;
                nextYear++;
            }
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    
    /**
     * Returns the previous date before this date
     * @return a new Date object representing the previous date
     */
    public Date previousDate() {
        int prevDay = m_day - 1;
        int prevMonth = m_month;
        int prevYear = m_year;

        if (prevDay<=0) {
            prevMonth--;
            if (prevMonth<=0) {
                prevMonth =12;
                prevYear--;
            }
            prevDay = getDaysInMonth(prevMonth, prevYear);
        }

        return new Date(prevDay, prevMonth, prevYear);
    }

    /**
     * Returns the number of days in a given month of a specific year.
     *
     * @param month the month for which to get the number of days
     * @param year  the year to consider for leap years
     * @return the number of days in the specified month
     */
    private static int getDaysInMonth(int month,int year) {
    	int day_fev;
    	
    	if(isLeapYear(year)) {
    		day_fev=29;
    	}else {
    		day_fev=28;
    	}
    	switch (month) {
        case 2: // feb
            return day_fev;  
        case 4: 
        case 6: 
        case 9: 
        case 11: // april,juin,sep,nov
            return 30; 
        default: // jan, mars, may,july,aug,oct,dec
            return 31; 
            }
    }

    /**
     * Compares this date to another date.
     *
     * @param other the date to compare to
     * @return a negative integer,zero, or a positive integer as this date is 
     *         earlier than, equal to, or later than the specified date.
     */
    @Override
    public int compareTo(Date other) {
        if (other==null) {
            throw new NullPointerException("Date cannot be null");
        }
        
        if (this.m_year!=other.m_year) {
            return this.m_year-other.m_year;
        }
        if (this.m_month!=other.m_month) {
            return this.m_month - other.m_month;
        }
        return this.m_day- other.m_day;
    }

    
    /**
     * Checks if this date is equal to another object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass()!= obj.getClass()) return false;
        Date date = (Date) obj;
        return (m_day == date.m_day)&&(m_month == date.m_month)&&(m_year==date.m_year);
    }

}

