## WebView中JS与java交互 ##

## common_dialog自定义dialog ##

## sortlistview ##
     根据名称对list进行排序
     case R.id.ordername:
    
                    name_bool = (name_bool ? false : true);
    
                    Collections.sort(mList, new Comparator<TestDate>() {
    
                        @Override
                        public int compare(TestDate lhs, TestDate rhs) {
    
                            if (name_bool) {
                                return lhs.getName().compareTo(rhs.getName());
                            } else {
                                return rhs.getName().compareTo(lhs.getName());
                            }
                        }
                    });
    
                    mAdapter.notifyDataSetChanged();
                    break;

  
         	        