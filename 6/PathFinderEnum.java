import java.util.Vector;

public enum PathFinderEnum implements PathFinderInterface
{
	    LEFT_HAND_TRAFFIC,
	    RIGHT_HAND_TRAFFIC;

		public boolean left()
		{
			return this == LEFT_HAND_TRAFFIC;
//			zwraca TRUE gdy mam isc w lewo
//			zwraca FALSE gdy mam isc w prawo
		}
		
		public boolean right()
		{
			return this == RIGHT_HAND_TRAFFIC;
//			analogicznie do powyzszej
		}
		
		private static int[][] FullMap;
		
		private int[][] tabCopy(int [][] tab)
		{
			int[][] array = new int[tab.length][tab[0].length];
			for(int i=0; i<tab.length; i++)
			{
				for(int j=0; j<tab[0].length; j++)
				{
					array[i][j] = tab[i][j];
				}
			}
			return array;
		}
		
	@Override
	public void setMap(int[][] map)
	{
		FullMap = map;	
	}
	
	private class Position implements PositionInterface
	{
		public int row;
		public int col;
		
		public Position(int c, int r)
		{
			this.row = r;
			this.col = c;
		}
		
		public int getRow()
		{
			return row;
		}
		
		public int getCol()
		{
			return col;
		}
	}
	
	private class Go
	{
		public PositionInterface place;
		public int direction; //1-North	2-East	3-South	4-West
		
		Go(PositionInterface p, int d)
		{
			this.place = p;
			this.direction = d;
		}
	}
	
	private class Turn
	{
		public Go move;
		public int lr; //1 - from there I went left; -1-... i went right
		
		Turn(Go m, int l)
		{
			this.move = m;
			this.lr = l;
		}
	}
	
	private Vector<Go> PointInfo (int[][]map, Go begin)
	{
		int maxRow = FullMap[0].length;
		int maxCol = FullMap.length;
		
		Vector<Go> info = new Vector<Go>();
		int row = begin.place.getRow();
		int col = begin.place.getCol();
		
		if(maxRow!=row+1 && map[col][row+1]>0)
		{
			Position point = new Position(col, row+1);
			Go next = new Go(point, 1);
			info.add(next);
		}
		if(row!=0 && map[col][row-1]>0)
		{
			Position point = new Position(col, row-1);
			Go next = new Go(point, 3);
			info.add(next);
		}
		if(col != 0 && map[col-1][row]>0)
		{
			Position point = new Position(col-1, row);
			Go next = new Go(point, 4);
			info.add(next);
		}
		if(maxCol!=col+1 && map[col+1][row]>0)
		{
			Position point = new Position(col+1, row);
			Go next = new Go(point, 2);
			info.add(next);
		}
		
		return info;
	}
	
	private Vector<Turn> Compare (Vector<Turn> first, Vector<Turn> second)
	{
		int Fturns[] = new int [3]; //0-straight 1-left 2-right
		int Sturns[] = new int[3];
		for(Turn f: first)
		{
			if(f.lr==0)
				Fturns[0]++;
			else if(f.lr==1)
				Fturns[1]++;
			else
				Fturns[2]++;
		}
		for(Turn s: second)
		{
			if(s.lr==0)
				Sturns[0]++;
			else if(s.lr==1)
				Sturns[1]++;
			else
				Sturns[2]++;
		}
		
		System.out.println("F " + Fturns[0] + " " + Fturns[1] + " " + Fturns[2]);
		System.out.println("S " + Sturns[0] + " " + Sturns[1] + " " + Sturns[2]);
		
		if(Fturns[0]!=Sturns[0])
		{
			if(Fturns[0]>Sturns[0])
				return first;
			else return second;
		}
		else
		{
			if(left())
			{
				if(Fturns[1]>Sturns[1])
					return first;
				else return second;
			}
			else
			{
				if(Fturns[2]>Sturns[2])
					return first;
				else return second;
			}
		}
	}
	
	private Vector<Vector<PositionInterface>> FindEasiest(int[][] map, Turn begin, PositionInterface end)
	{
		Vector<Vector<PositionInterface>> AllWays = new Vector<Vector<PositionInterface>>();
		
		System.out.println("Jestem w " + begin.move.place.getCol() +  " " + begin.move.place.getRow());
		
		Vector<Go> begInfo = PointInfo(map, begin.move);
		
//		jestem u celu
		if(begin.move.place.getCol()==end.getCol() && begin.move.place.getRow()==end.getRow())
		{
			Vector<PositionInterface> last = new Vector<PositionInterface>();
			last.add(end);
			AllWays.add(last);
			System.out.println("koniec");
			return AllWays;
		}

//		odbieram mozliwosc cofania sie
		int [][] NextMap = tabCopy(map);
		NextMap[begin.move.place.getCol()][begin.move.place.getRow()] = 0;

		
//		nie mam gdzie isc
		if(begInfo == null)
		{
			return AllWays;
		}

		Go CanStraight = null;
		
		for(Go goNext: begInfo)
		{
			if(begin.move.direction==goNext.direction)
			CanStraight = goNext;
		}
		
		Vector<Vector<PositionInterface>> result;

		if(CanStraight!=null)
		{
			Turn next = new Turn(CanStraight, 0); //go straight
			System.out.println("prosto");
			result =  FindEasiest(NextMap, next, end);
			for(Vector<PositionInterface> res: result)
			{
				res.add(0, CanStraight.place);
				AllWays.add(res);
				System.out.println("Dodalem krok prosto");
			}
		}
		else
		{
			for(Go goNext: begInfo)
			{
				Turn next;
				Vector<Vector<PositionInterface>> resultT;
				
				if(begin.move.direction==0)
				{
					next = new Turn(goNext, 0); //start, always straight
					System.out.println("start");
					resultT =  FindEasiest(NextMap, next, end);
					for(Vector<PositionInterface> res: resultT)
					{
						res.add(0, goNext.place);
						res.add(0, begin.move.place);
						res.remove(res.size()-1);
						AllWays.add(res);
						System.out.println("Dodalem krok start");
					}
				}
				else if(right())
				{
					if(goNext.direction>begin.move.direction || (goNext.direction==1 && begin.move.direction==4))
					{
						next = new Turn(goNext, -1);	//go right
						System.out.println("W prawo");
						resultT =  FindEasiest(NextMap, next, end);
						for(Vector<PositionInterface> res: resultT)
						{
							res.add(0, goNext.place);
							AllWays.add(res);
							System.out.println("Dodalem krok w prawo");
						}
					}
					else if(goNext.direction<begin.move.direction || (goNext.direction==4 && begin.move.direction==1))
					{
						next = new Turn(goNext, 1); //go left
						System.out.println("W lewo");
						resultT =  FindEasiest(NextMap, next, end);
						for(Vector<PositionInterface> res: resultT)
						{
							res.add(0, goNext.place);
							AllWays.add(res);
							System.out.println("Dodalem krok w lewo");
						}
					}	
				}
				else
				{
					if(goNext.direction<begin.move.direction || (goNext.direction==4 && begin.move.direction==1))
					{
						next = new Turn(goNext, 1); //go left
						System.out.println("W lewo");
						resultT =  FindEasiest(NextMap, next, end);
						for(Vector<PositionInterface> res: resultT)
						{
							res.add(0, goNext.place);
							AllWays.add(res);
							System.out.println("Dodalem krok w lewo");
						}
					}
					else if(goNext.direction>begin.move.direction || (goNext.direction==1 && begin.move.direction==4))
					{
						next = new Turn(goNext, -1);	//go right
						System.out.println("W prawo");
						resultT =  FindEasiest(NextMap, next, end);
						for(Vector<PositionInterface> res: resultT)
						{
							res.add(0, goNext.place);
							AllWays.add(res);
							System.out.println("Dodalem krok w prawo");
						}
					}
				}
			}
		}
		
		return AllWays;
	}

	private Vector<Vector<Turn>> FindWays(int[][] map, Turn begin, PositionInterface end)
	{
		Vector<Vector<Turn>> AllWays = new Vector<Vector<Turn>>();
		
		System.out.println("Jestem w " + begin.move.place.getCol() +  " " + begin.move.place.getRow());
		
		Vector<Go> begInfo = PointInfo(map, begin.move);
		
//		jestem u celu
		if(begin.move.place.getCol()==end.getCol() && begin.move.place.getRow()==end.getRow())
		{
			Vector<Turn> last = new Vector<Turn>();
			last.add(begin);
			AllWays.add(last);
			System.out.println("koniec");
			return AllWays;
		}

//		odbieram mozliwosc cofania sie
		
//		nie mam gdzie isc
		if(begInfo == null)
		{
			return AllWays;
		}

		int [][] NextMap = tabCopy(map);
		NextMap[begin.move.place.getCol()][begin.move.place.getRow()] = 0;
		
		if(begInfo.size()==1)
		{
			Turn next = new Turn(begInfo.get(0), 0); //go straight
			if(begin.move.direction==0)
			{
				//start, always straight
				System.out.println("start");
				Vector<Vector<Turn>> resultT = FindWays(NextMap, next, end);
				for(Vector<Turn> res: resultT)
				{
					res.add(0, next);
					res.add(0, begin);
					res.remove(res.size()-1);
					AllWays.add(res);
					System.out.println("Dodalem krok start");
				}
			}
			else
			{
				System.out.println("prosto");
				Vector<Vector<Turn>> resultT = FindWays(NextMap, next, end);
				for(Vector<Turn> res: resultT)
				{
					res.add(0, next);
					AllWays.add(res);
					System.out.println("Dodalem krok prosto");
				}				
			}
		}
		else
		for(Go goNext: begInfo)
		{
			Turn next;				
			Vector<Vector<Turn>> resultT;
			
			if(begin.move.direction==0)
			{
				next = new Turn(goNext, 0); //start, always straight
				System.out.println("start");
				resultT = FindWays(NextMap, next, end);
				for(Vector<Turn> res: resultT)
				{
					res.add(0, next);
					res.add(0, begin);
					res.remove(res.size()-1);
					AllWays.add(res);
					System.out.println("Dodalem krok start");
				}
			}
			else if(goNext.direction>begin.move.direction || (goNext.direction==1 && begin.move.direction==4))
			{
				next = new Turn(goNext, -1);	//go right
				System.out.println("W prawo");
				resultT = FindWays(NextMap, next, end);
				for(Vector<Turn> res: resultT)
				{
					res.add(0, next);
					AllWays.add(res);
					System.out.println("Dodalem krok w prawo");
				}
			}
			else if(goNext.direction<begin.move.direction || (goNext.direction==4 && begin.move.direction==1))
			{
					next = new Turn(goNext, 1); //go left
					System.out.println("W lewo");
					resultT = FindWays(NextMap, next, end);
					for(Vector<Turn> res: resultT)
					{
						res.add(0, next);
						AllWays.add(res);
						System.out.println("Dodalem krok w lewo");
					}
				}	
			else
			{
				next = new Turn(goNext, 0); //go straight
				System.out.println("prosto");
				resultT = FindWays(NextMap, next, end);
				for(Vector<Turn> res: resultT)
				{
					res.add(0, next);
					AllWays.add(res);
					System.out.println("Dodalem krok prosto");
				}
			}	
		}
		return AllWays;
	}
	
	@Override
	public PositionInterface[] getShortestRoute(PositionInterface begin, PositionInterface end)
	{
		int[][] NewMap = FullMap;
//		gdy miejsce w którym zaczynam to 0
		if(FullMap[begin.getCol()][begin.getRow()]==0)
		{
			return null;
		}
		Turn begin1 = new Turn(new Go(begin, 0), 0);
		Vector<Vector<Turn>> ways = FindWays(NewMap, begin1, end);
		int j=1;
		
		if(ways.size()!=0)
		{
			Vector<Turn> result = ways.get(0);
			
			for(Vector<Turn> p: ways)
			{
				System.out.println("Rozwiazanie " + j);	
				j++;
				for(Turn n: p)
				{
					System.out.println("\t" + n.move.place.getCol() + " " + n.move.place.getRow());
				}
				
				if(result.size()>p.size())
					result = p;
				else if(result.size()==p.size())
				{
					result = Compare(result, p);
				}
			}
			
			PositionInterface[] Shortest = new PositionInterface[result.size()];
			for(int i=0; i<result.size(); i++)
			{
				Shortest[i] = result.get(i).move.place;
				
			}
			return Shortest;			
		}
		else return null;
		
	}

	@Override
	public PositionInterface[] getEasiestRoute(PositionInterface begin, PositionInterface end)
	{
		int[][] NewMap = FullMap;
//		gdy miejsce w którym zaczynam to 0
		if(FullMap[begin.getCol()][begin.getRow()]==0)
		{
			return null;
		}
		Turn begin1 = new Turn(new Go(begin, 0), 0);
		Vector<Vector<PositionInterface>> ways = FindEasiest(NewMap, begin1, end);
		int j=1;
		
		if(ways.size()!=0)
		{
			Vector<PositionInterface> result = ways.get(0);
			
				System.out.println("Rozwiazanie " + j);	
				for(PositionInterface n: result)
				{
					System.out.println("\t" + n.getCol() + " " + n.getRow());
				}
			
			PositionInterface[] Easiest = new PositionInterface[result.size()];
			for(int i=0; i<result.size(); i++)
			{
				Easiest[i] = result.get(i);
				
			}
			return Easiest;			
		}
		else return null;
//		int[][] NewMap = FullMap;
////		gdy miejsce w którym zaczynam to 0
//		if(FullMap[begin.getCol()][begin.getRow()]==0)
//		{
//			return null;
//		}
//		Turn begin1 = new Turn(new Go(begin, 0), 0);
//		Vector<Vector<Turn>> ways = FindWays(NewMap, begin1, end);
//		int j=1;
//		
//		if(ways.size()!=0)
//		{
//			Vector<Turn> result = ways.get(0);
//			
//			for(Vector<Turn> p: ways)
//			{
//				System.out.println("Rozwiazanie " + j);	
//				j++;
//				for(Turn n: p)
//				{
//					System.out.println("\t" + n.move.place.getCol() + " " + n.move.place.getRow());
//				}
//					result = Compare(result, p);
//			}
//			
//			PositionInterface[] Shortest = new PositionInterface[result.size()];
//			for(int i=0; i<result.size(); i++)
//			{
//				Shortest[i] = result.get(i).move.place;
//				
//			}
//			return Shortest;			
//		}
//		else return null;
		
	}

	@Override
	public PositionInterface[] getFastestRoute(PositionInterface begin, PositionInterface end) 
	{
		int[][] NewMap = FullMap;
//		gdy miejsce w którym zaczynam to 0
		if(FullMap[begin.getCol()][begin.getRow()]==0)
		{
			return null;
		}
		Turn begin1 = new Turn(new Go(begin, 0), 0);
		Vector<Vector<Turn>> ways = FindWays(NewMap, begin1, end);
		int j=1;
		
		if(ways.size()!=0)
		{
			Vector<Turn> result = ways.get(0);
			int before = 1000;
			
			for(Vector<Turn> p: ways)
			{
				int sum =0;
				System.out.println("Rozwiazanie " + j);	
				j++;
				for(Turn n: p)
				{
					System.out.println("\t" + n.move.place.getCol() + " " + n.move.place.getRow());
					sum+=FullMap[n.move.place.getCol()][n.move.place.getRow()];
				}
				
				if(before>sum)
				{
					result= p;
					before = sum;
				}
				else if(before==sum)
				{
					result = Compare(result, p);
				}
			}
			
			PositionInterface[] Shortest = new PositionInterface[result.size()];
			for(int i=0; i<result.size(); i++)
			{
				Shortest[i] = result.get(i).move.place;
				
			}
			return Shortest;			
		}
		else return null;
	}

}
