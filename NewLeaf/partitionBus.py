import pandas as pd

df_toronto = pd.read_csv('/Users/magurman/Library/Mobile Documents/com~apple~CloudDocs/ALIGN/CS 5200/Mudhens/MudHens/data/V2_Biz.csv')

category_list_unique = [] #this will be list of unique categorie lists

for i in range(len(df_toronto['categories'])):
    
    # populate list of unique rows of category
    cat = df_toronto['categories'][i]
    if cat in category_list_unique:
        pass
    else:
        category_list_unique.append(cat)
        
        
cat_unique_freq = {} # count frequency of category 

for i in category_list_unique:
    
    if isinstance(i, str):
    
        category_row = i.split(", ")
    
        for j in category_row:
            if j in cat_unique_freq.keys():
                cat_unique_freq[j] = cat_unique_freq.get(j) + 1
            else:
                cat_unique_freq[j] = 1
                
cat_unique_freq_sorted = sorted(cat_unique_freq.items(), key=lambda x: x[1],
                                reverse=True)


for i in range(50):
    print(cat_unique_freq_sorted[i])
    

df_columns = ['business_id', 'name', 'stars', 'review_count', 'attributes',
              'categories', 'hours', 'LocationKey']

df_nightlife = pd.DataFrame(columns=df_columns)
df_leisure = pd.DataFrame(columns=df_columns)
df_universities = pd.DataFrame(columns=df_columns)
df_parks = pd.DataFrame(columns=df_columns)
df_k12 = pd.DataFrame(columns=df_columns)
df_pub_trans = pd.DataFrame(columns=df_columns)

for index, row in df_toronto.iterrows():
    if isinstance(row['categories'], str):
        categories = row['categories']
        categories = categories.split(", ")
        
        if ("Restaurants" in categories or "Nightlife" in categories 
            or "Food" in categories or "Bar" in categories):
            
            df_nightlife = df_nightlife.append(row)
            
        elif ("Coffee & Tea" in categories or "Beauty & Spas" in categories 
                 or "Cafes" in categories or "Arts & Entertainment" in categories
                 or "Fasion" in categories or "Amusement Parks" in categories
                 or "Arcades" in categories):
            
            df_leisure = df_leisure.append(row)
            
        elif ("Education" in categories or "University Housing" in categories
              or "Colleges & Universities" in categories):
            
            df_universities = df_universities.append(row)
        
        elif ("Parks" in categories or "Playgrounds" in categories):
            
            df_parks = df_parks.append(row)
            
        elif ("Preschools" in categories or "Child Care & Day Care" in categories
              or "Middle Schools & High Schools" in categories 
              or "Elementary Schools" in categories):
            
            df_k12 = df_k12.append(row)
        
        elif ("Public Transportation" in categories 
              or "Train Stations" in categories):
            
            df_pub_trans = df_pub_trans.append(row)
            
                    

df_nightlife.to_csv('T_nightlife.csv')
df_leisure.to_csv('T_leisure.csv')
df_universities.to_csv('T_education.csv')
df_parks.to_csv('T_parks.csv')
df_k12.to_csv('T_k12.csv')
df_pub_trans.to_csv('T_pub_trans.csv')