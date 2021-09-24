from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class SmallCategory(db.Model):
  __tablename__ = "small_category"

  id = db.Column(db.Integer, nullable=False, autoincrement=True, primary_key=True)
  name = db.Column(db.String(45), nullable=False)
  big_category_id = db.Column(db.Integer)

  def __repr__(self):
    return f"[SmallCategory] ({self.id}, {self.name}, {self.big_category_id})"

  def as_dict(self):
    return {x.name: getattr(self, x.name) for x in self.__table__.columns}